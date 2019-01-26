package com.lpy.scm.manager.impl;

import com.lpy.scm.BaseLockSign;
import com.lpy.scm.constants.RedisConstants;
import com.lpy.scm.log.RedisLog;
import com.lpy.scm.manager.RedisManager;
import com.lpy.scm.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author lpy
 * @date 2019/1/24 17:45
 */
@Component("redisManager")
public class RedisManagerImpl extends RedisConstants implements RedisManager {

    private static JedisPool jedisPool = null;
    private static RedisLog logger = new RedisLog();
    private static String rateLimitShaStr;

    public static Map<Integer, Long> initMap = new ConcurrentHashMap<>();


    private static String JEDIS_HOST;
    private static String JEDIS_PORT;
    private static String JEDIS_AUTH;

    //   初始化Redis连接池
    static {
        initJedisPool();
        initSpecialScript();
    }

    private static void initSpecialScript() {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("####redis init error, init failed");
            return;
        }
        try {
            rateLimitShaStr = jedis.scriptLoad(RATE_LIMIT_SCRIPT);
        } catch (Exception e) {
            logger.error("####redis init error rateLimitShaStr init failed", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 获取Jedis实例
     */
    public static Jedis getJedis() {
        Jedis jedis = null;
        int count = 0;
        do {
            try {
                if (jedisPool == null) {
                    initJedisPool();
                }
                jedis = jedisPool.getResource();
            } catch (Exception e) {
                logger.error("Redis Exception :", e);
                logger.warn("时间" + DateUtil.standardFormat.get().format(new Date()) + ",jedisPool中实例耗尽," +
                        "active=" + jedisPool.getNumActive() + ",idle=" + jedisPool.getNumIdle());
                jedisPool = null;
                initJedisPool();// 防止意外情况资源耗尽 重新初始化jedisPool 这个只是一解燃眉之急而已 最终解决还是要在程序中注意及时释放连接实例
            }
            count++;
        } while (jedis == null && count < REDIS_RETRY_COUNT);//重试10次
        return jedis;
    }

    /**
     * @description 初始化jedisPool 不保证只有一个线程初始化成功  保证十秒内只初始化一次
     */
    private static synchronized void initJedisPool() {
        Long lastInitTime = initMap.get(101);
        if (lastInitTime != null && System.currentTimeMillis() - lastInitTime <= 10000) {
            return;
        } else {
            initMap.put(101, System.currentTimeMillis());
        }
        FileInputStream in = null;//System.getenv("CC_RESOURCE");
        try {
            in = new FileInputStream(new File(System.getenv("SCM") + "/jedis.properties"));
            Properties prop = new Properties();
            prop.load(in);

            Properties envUat = PropertiesLoaderUtils.loadAllProperties("redis.properties");
            JEDIS_PORT =  envUat.getProperty("redis.host");
            JEDIS_AUTH =  envUat.getProperty("redis.auth");
            JEDIS_HOST =  envUat.getProperty("redis.host");

            String ADDR = StringUtils.isEmpty(JEDIS_HOST) ? prop.getProperty("jedis.host").trim() : JEDIS_HOST;
            int PORT = StringUtils.isEmpty(JEDIS_PORT) ? Integer.parseInt(prop.getProperty("jedis.port").trim()) : Integer.valueOf(JEDIS_PORT);
            String AUTH = StringUtils.isEmpty(JEDIS_AUTH) ? prop.getProperty("jedis.auth").trim() : JEDIS_AUTH;
            JedisPoolConfig config = initConfig();
            logger.info("ADDR:" + ADDR + ",PORT:" + PORT + ",AUTH:" + AUTH);
            if (StringUtils.isEmpty(AUTH)) {
                jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT);
            } else {
                jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
            }
            logger.info("Redis连接池初始化成功，Host" + ADDR + ",PORT:" + PORT);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            System.err.println("####Redis 连接配置文件 jedis.properties 加载失败！");
            logger.error("####Redis 连接配置文件 jedis.properties 加载失败！");
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.warn(e.getMessage(), e);
                }
            }
        }
    }

    private static JedisPoolConfig initConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);///表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出 　
        config.setMinEvictableIdleTimeMillis(MIN_IDLE_TIME);
        config.setTestOnBorrow(TEST_ON_BORROW);
        return config;
    }

    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = getJedis();
        Map<String, String> map = new HashMap<>();
        if (null == jedis) {
            return map;
        }
        try {
            map = jedis.hgetAll(key);
            long len=jedis.hlen(key);
            if(len>100){
              /*  DingUtil.sendDingMsg(SettingsHolder.getProperty(SettingsEnum.TEST_DING_TOKEN),
                        "redis hgetall 命令使用","集合长度超过了100 ,key="+key+",hlen="+len,true);*/
            }
        } catch (Exception e) {
            logger.error("redis hget failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return map;
    }


    @Override
    public List hscan(String key, ScanParams params, Jedis jedis) {
        List  list=new ArrayList();
        boolean closeFlag=false;
        try {
            if(jedis==null){
                jedis=getJedis();
                closeFlag=true;
            }
            if (null == jedis) {
                return list;
            }
            getHscanAllData(key, params, jedis, list);

        } catch (Exception e) {
            logger.error("redis hscan failed, key=" + key);
        } finally {
            if (jedis != null&&closeFlag) {
                jedis.close();
            }
        }
        return list;
    }

    /**
     * @description  扫描结束获取到的result的cursor会变为0
     */
    private void getHscanAllData(String key, ScanParams params, Jedis jedis, List list) {
        ScanResult result;
        String cursor="0";
        do {
            result = jedis.hscan(key,cursor,params);
            list.addAll(result.getResult());
            cursor=result.getStringCursor();
        }while(!("0".equals(cursor)));
    }

    public Map<String, String> hgetAll(String key, Jedis jedis) {
        return jedis.hgetAll(key);
    }

    @Override
    public String hget(String key, String field) {
        Jedis jedis = getJedis();
        String str = "";
        if (null == jedis) {
            return str;
        }
        try {
            str = jedis.hget(key, field);
        } catch (Exception e) {
            logger.error("redis hget failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return str;
    }

    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis jedis = getJedis();
        List<String> list = new ArrayList<>();
        if (null == jedis) {
            return list;
        }
        try {
            list = jedis.hmget(key, fields);
        } catch (Exception e) {
            logger.error("redis hget failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return list;
    }

    @Override
    public long hset(String key, String field, String value) {
        Jedis jedis = getJedis();
        long count = 0L;
        if (null == jedis) {
            return count;
        }
        try {
            count = jedis.hset(key, field, value);
        } catch (Exception e) {
            logger.error("redis hset failed, key=" + key + ",value=" + value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return count;
    }

    @Override
    public long hset(String key, String field, String value, int expireTime) {
        Jedis jedis = getJedis();
        long count = 0L;
        if (null == jedis) {
            return count;
        }
        try {
            Pipeline pipeline = jedis.pipelined();
            Response<Long> response = pipeline.hset(key, field, value);
            pipeline.expire(key, expireTime);
            pipeline.sync();
            //管道的使用注意点, 获取结果需要在sync方法执后
            count = response.get();
        } catch (Exception e) {
            logger.error("redis hset failed, key=" + key + ",value=" + value, e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return count;
    }

    @Override
    public void hmset(String key, Map<String, String> fieldValueMap) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        try {
            jedis.hmset(key, fieldValueMap);
        } catch (Exception e) {
            logger.error("redis hmset failed, key=" + key + ",fieldValueMap=" + fieldValueMap);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }

    }

    @Override
    public void set(String key, String value) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            logger.error("redis set failed, key=" + key + ",value=" + value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public String get(String key) {
        Jedis jedis = getJedis();
        String value = "";
        if (null == jedis) {
            logger.error("can not get jedis");
            return value;
        }
        try {
            value = jedis.get(key);
        } catch (Exception e) {
            logger.error("redis get failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return value;
    }

    @Override
    public void set(String key, String value, int expireTime) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        try {
            jedis.set(key, value);
            jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("redis set expire failed, key=" + key + ",value=" + value);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public long expire(String key, int expireTime) {
        Jedis jedis = getJedis();
        long time = 0L;
        if (null == jedis) {
            logger.error("can not get jedis");
            return time;
        }
        try {
            time = jedis.expire(key, expireTime);
        } catch (Exception e) {
            logger.error("redis del failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return time;
    }

    @Override
    public long del(String key) {
        Jedis jedis = getJedis();
        long count = 0L;
        if (null == jedis) {
            logger.error("can not get jedis");
            return count;
        }
        try {
            count = jedis.del(key);
        } catch (Exception e) {
            logger.error("redis del failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return count;
    }

    @Override
    public long del(String... keys) {
        Jedis jedis = getJedis();
        long count = 0L;
        if (null == jedis) {
            logger.error("can not get jedis");
            return count;
        }
        try {
            count = jedis.del(keys);
        } catch (Exception e) {
            logger.error("redis del failed, key=" + keys);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return count;
    }

    @Override
    public long hdel(String key, String field) {
        Jedis jedis = getJedis();
        long count = 0L;
        if (null == jedis) {
            logger.error("can not get jedis");
            return count;
        }
        try {
            count = jedis.hdel(key, field);
        } catch (Exception e) {
            logger.error("redis hdel failed, key=" + key + ",field=" + field);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return count;
    }

    @Override
    public long hsetnx(String key, String field, String value) {
        Jedis jedis = getJedis();
        long count = 0L;
        if (null == jedis) {
            logger.error("can not get jedis");
            return count;
        }
        try {
            count = jedis.hsetnx(key, field, value);
        } catch (Exception e) {
            logger.error("redis hsetNx failed, key=" + key + ",field=" + field);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return count;
    }

    @Override
    public long setnx(String key, String value){
        Jedis jedis = getJedis();
        long count = 0L;
        if (null == jedis) {
            logger.error("can not get jedis");
            return count;
        }
        try {
            count = jedis.setnx(key, value);
        } catch (Exception e) {
            logger.error("redis setNx failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return count;
    }

    @Override
    public void incr(String tradeKey, int expireTime) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        long count = 0L;
        try {
            count = jedis.incr(tradeKey);
            if (expireTime > 0) {
                jedis.expire(tradeKey, expireTime);
            }
        } catch (Exception e) {
            logger.error("redis incr failed, key=" + tradeKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void incr(String tradeKey) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        long count = 0L;
        try {
            count = jedis.incr(tradeKey);
        } catch (Exception e) {
            logger.error("redis incr failed, key=" + tradeKey);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void zadd(String key, long l, String value) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        long count = 0L;
        try {
            count = jedis.zadd(key, l, value);
        } catch (Exception e) {
            logger.error("redis incr failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }


    @Override
    public boolean reachRateLimit(String key, String timeSeconds, String limitTimes) {

        Jedis jedis = getJedis();
        long result = 1;
        if (null == jedis) {
            logger.error("can not get jedis");
            return true;
        }
        try {
            String shaStr = jedis.get("ipRateLimitLuaSha");
            shaStr = StringUtils.isEmpty(shaStr) ?
                    rateLimitShaStr : shaStr;
            Object o = jedis.evalsha(shaStr, 1, key, timeSeconds, limitTimes);
            result = (long) o;
        } catch (Exception e) {
            logger.error("redis reachRateLimit script failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result == 0;
    }

    public boolean reachRateLimit(String key, String timeSeconds, String limitTimes, Jedis jedis) {
        String shaStr = jedis.get("ipRateLimitLuaSha");
        shaStr = StringUtils.isEmpty(shaStr) ?
                rateLimitShaStr : shaStr;
        Object o = jedis.evalsha(shaStr, 1, key, timeSeconds, limitTimes);
        long result = (long) o;
        return result == 0;
    }

    @Override
    public boolean lock(String lockKey, String lockModel, BaseLockSign lockThreadLocal) {
        int timeOut=timeoutMsecs;
        return lockDefaultStrategy(lockKey,lockModel,lockThreadLocal,timeOut);
    }

    @Override
    public boolean lock(String lockKey, String lockModel, BaseLockSign lockThreadLocal, int timeOut) {

        return lockDefaultStrategy(lockKey,lockModel,lockThreadLocal,timeOut);
    }

    private boolean lockDefaultStrategy(String lockKey, String lockModel, BaseLockSign lockThreadLocal,int timeOut) {
        logger.info("lock lockThreadLocalName=" + lockThreadLocal.getLockLocalName() );
        //重复使用锁的情况 只加一次
        if (lockThreadLocal.getLockSign()) {
            return true;
        }
        Jedis jedis = getJedis();
        //jedis连接获取不到的情况直接返回true 也即redis lock不再起效 所有进来的都直接认为成功
        if (jedis == null || !StringUtils.isEmpty(jedis.get(RedisConstants.REDIS_LOCK_SWITCH))) {
            return true;
        }
        boolean lockSign = false;
        try {
            boolean lockModelHighCostAllow = StringUtils.isEmpty(jedis.get(LOCK_MODEL));
            //全局允许且采用高性能重试模式
            if (lockModelHighCostAllow && LOCK_MODEL_ALLOW_RETRY.equals(lockModel)) {
                /*重试模式 场景举例  甲客户充值时遇到task占用余额lock 此时可以等待一秒钟左右进行重试
                  task操作完后可以继续完成充值 而不是直接返回系统繁忙的提示 这样体验不是很好 系统容错差
                  延迟一定的毫秒数,最多延迟一秒钟  这里使用随机时间可能会好一点,可以防止饥饿进程的出现,即,当同时到达多个进程,
                  只会有一个进程获得锁,其他的都用同样的频率进行尝试,后面有来了一些进程,也以同样的频率申请锁,这将可能导致前面来的锁得不到满足.
                  使用随机的等待时间可以一定程度上保证公平性
                 */
                lockSign = retryGetLock(jedis, lockKey,timeOut);
            } else {
                //全局不允许或者不使用重试模式都走此种锁模式
                //不提供重试 适用于秒杀抢夺一件产品的场景 直接返回秒杀失败即可
                lockSign = tryGetLock(jedis, lockKey);
            }
        } catch (Exception e) {
            logger.error("redis lock  failed, lockKey=" + lockKey, e);
        } finally {
            jedis.close();
        }
        //只有获得lock的本线程才设置lockSign为true

        if (lockSign) {
            //本地线程变量需要谨慎使用，大批量使用时尤其注意要 用完调用remove方法释放 否则会引起内存泄漏
            //参考博客 https://www.jianshu.com/p/33c5579ef44f
            setThreadLocalLockSign(lockThreadLocal, true);
        }
        return lockSign;
    }

    private void setThreadLocalLockSign(BaseLockSign lockThreadLocal, boolean lockSign) {
        lockThreadLocal.setLockSign(lockSign);
    }

    private boolean retryGetLock(Jedis jedis, String lockKey,int timeoutMsecs) throws InterruptedException {
        int timeout = timeoutMsecs;
        int waitMsecs = new Random().nextInt(100) + 100;//100~200ms
        int retryCou = 0;
        while (timeout >= 0) {
            if (tryGetLock(jedis, lockKey)) {
                return true;
            }
            timeout -= waitMsecs;
            Thread.sleep(waitMsecs);
            retryCou++;
        }
        if (retryCou > 0) {
            logger.info("retryGetLock failed,thread=" + Thread.currentThread().getName() + ",retryCou=" + retryCou + ",lockKey=" + lockKey);
        }
        return false;
    }

    private boolean tryGetLock(Jedis jedis, String lockKey) {
        long expires = System.currentTimeMillis() + expireMsecs;
        String expiresStr = String.valueOf(expires); //锁到期时间
        if (jedis.setnx(lockKey, expiresStr) == 1) {//首次设置该值，直接返回成功 获得锁 返回
            // lock acquired
            return true;
        }
        // 不是第一次设置该锁 ，也就是别人抢先了，但万一那人有什么情况
        // （倒霉的redis挂了且key的信息还没同步好 亿万分之一的几率 或者超时 太磨叽）总之锁又被释放了
        //  那别人就又有竞争机会了 继续进行下面的尝试
        String currentValueStr = this.get(lockKey); //redis里的时间
        if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
            //判断是否为空，不为空的情况下，如果被其他线程设置了值，则第二个条件判断是过不去的
            // lock is expired
            String newExpireTime = String.valueOf(System.currentTimeMillis() + expireMsecs);
            //竞态获取资格
            String oldValueStr = jedis.getSet(lockKey, newExpireTime);
            //获取上次的到期时间，并设置现在的锁到期时间，
            if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void unLock(String key, BaseLockSign lockThreadLocal) {
        try{

            logger.info("unLock lockThreadLocalName=" + lockThreadLocal.getLockLocalName() );

            //针对余额操作的分布式锁
            if (threadLocalLockSignTrue(lockThreadLocal)) {
             /*  try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
                logger.info(Thread.currentThread().getName() + ",lockLocal=" + lockThreadLocal.toString() + " redis unLock acquired,unlockKey=" + key);
                //使用ThreadLocal就跟加锁完要解锁一样，用完就清理  以免引起内存泄漏
                del(key);
            }
            //必须删除本地线程变量否则会引起内存泄漏
            //参考博客 https://www.jianshu.com/p/33c5579ef44f
            lockThreadLocal.clear();
        } catch (Exception e) {
            logger.warn(Thread.currentThread().getName() + ",lockLocal=" + lockThreadLocal.toString() + " redis unLock failed,unlockKey=" + key, e);

        }
    }

    private boolean threadLocalLockSignTrue(BaseLockSign lockThreadLocal) {
        return lockThreadLocal.getLockSign();
    }

    @Override
    public Set<String> smembers(String key) {
        Jedis jedis = getJedis();
        Set<String> members = new HashSet<>();
        if (null == jedis) {
            logger.error("can not get jedis");
            return members;
        }
        try {
            members = jedis.smembers(key);
        } catch (Exception e) {
            logger.error("redis smembers failed, key=" + key);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return members;
    }

    public Set<String> smembers(String key, Jedis jedis) {
        return jedis.smembers(key);
    }


    @Override
    public void sadd(String key, String value) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        try {
            jedis.sadd(key, value);
        } catch (Exception e) {
            logger.warn("ipUaInfoReachLimit failed", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    /**
     * 判断成员元素是否是集合的成员
     *
     * @param key
     * @param member
     * @return
     */
    @Override
    public boolean sismember(String key, String member) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return false;
        }
        try {
            return jedis.sismember(key, member);
        } catch (Exception e) {
            logger.warn("sismember failed", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }



    @Override
    public Long publish(String channel, String message) {

        Jedis jedis = getJedis();
        Long result = null;
        if (null == jedis) {
            logger.error("can not get jedis");
            return null;
        }
        try {
            result = jedis.publish(channel, message);
        } catch (Exception e) {
            logger.error("redis publish failed, channel=" + channel + ",message=" + message);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String describe, String... channels) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        try {
            jedis.subscribe(jedisPubSub, channels);
        } catch (Exception e) {
            logger.error("redis subscribe failed, jedisPubSubName=" + describe + ",channels=" + Arrays.toString(channels));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public void psubscribe(JedisPubSub jedisPubSub, String describe, String... patterns) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        try {
            jedis.psubscribe(jedisPubSub, patterns);
        } catch (Exception e) {
            logger.error("redis psubscribe failed, jedisPubSubName=" + describe + ",patterns=" + Arrays.toString(patterns));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public Double zscore(String key, String member) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return null;
        }
        Double result = null;
        try {
            result = jedis.zscore(key, member);
        } catch (Exception e) {
            logger.error("redis zscore failed, key=" + key + ",member=" + member);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return result;
    }

    @Override
    public void zrem(String key, String member) {
        Jedis jedis = getJedis();
        if (null == jedis) {
            logger.error("can not get jedis");
            return;
        }
        try {
            jedis.zrem(key, member);
        } catch (Exception e) {
            logger.error("redis zrem failed, key=" + key + ",member=" + member);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    @Override
    public boolean hexists(String key, String childKey,Jedis jedis) {
        boolean closeFlag=false;
        try {
            if(jedis==null){
                jedis=getJedis();
                closeFlag=true;
            }
            if (null == jedis) {
                return false;
            }
            return jedis.hexists(key,childKey);

        } catch (Exception e) {
            logger.error("redis hexists failed, key=" + key);
        } finally {
            if (jedis != null&&closeFlag) {
                jedis.close();
            }
        }
        return false;
    }

    @Override
    public boolean exists(Jedis jedis, String key) {
        boolean closeFlag = false;
        if (jedis == null) {
            jedis = getJedis();
            closeFlag = true;
        }
        boolean flag = false;
        if (null == jedis) {
            logger.error("can not get jedis, key=" + key);
            return false;
        }
        try {
            flag = jedis.exists(key);
        } catch (Exception e) {
            logger.warn("redis exists failed, key =" + key, e);
        } finally {
            if (jedis != null && closeFlag) {
                jedis.close();
            }
        }
        return flag;
    }



    static class CCTestPubSub extends JedisPubSub {

        @Override
        public void onMessage(String channel, String message) {
            System.out.println(message);
        }

        @Override
        public void onSubscribe(String channel, int subscribedChannels) {

            System.out.println(channel);

        }

    }

    private static void publishTest(Jedis jedis) {
        jedis.publish("cc-pb-test-01", " pubsub test lalalal");
        jedis.close();

    }

    private static void subscribeTest(Jedis jedis) {
        CCTestPubSub sub = new CCTestPubSub();
        jedis.subscribe(sub, "cc-pb-test-01");

    }

}