package com.lpy.scm.constants;

/**
 * @author lpy
 * @date 2019/1/24 17:46
 */
public class RedisConstants {
    /**
     * redis 全局配置
     */
    public static final int REDIS_RETRY_COUNT = 10;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    public final static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    public final static int MAX_IDLE = 200;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    public final static int MAX_WAIT = 10000;
    public final static int TIMEOUT = 10000;
    //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
    public final static int MIN_IDLE_TIME = 60 * 1000 * 5;//5分钟释放
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    public final static boolean TEST_ON_BORROW = true;


    //频率限制‘
    public static final String RATE_LIMIT_SCRIPT = "if redis.call('exists',KEYS[1])==1 and tonumber(redis.call('get',KEYS[1]))>tonumber(ARGV[2]) then return 0 end local times = redis.call('incr',KEYS[1]) if times == 1 then redis.call('expire',KEYS[1],ARGV[1])   end if times>tonumber(ARGV[2]) then return 0 end return 1";
    //频率限制校验开关标识
    public static final String SETTLE_RATE_LIMIT_SIGN = "SETTLE_RATE_LIMIT_SIGN";
    //redis lock用常量
    //锁允许重试模式
    public static final String LOCK_MODEL_ALLOW_RETRY = "allowRetry";
    //分布式锁开关 默认为空不打开
    public static final String REDIS_LOCK_SWITCH = "ps-str-redis-lock-switch";

    /**
     * redis lock模式  不为空则说明使用降级模式
     * 一旦lock被某一线程持有 其他线程直接返回false 不在进行重试操作 降低线程sleep重试的性能损耗
     */
    public static final String LOCK_MODEL = "ps_str_lock_model";

    /**
     * 锁等待时间，防止线程饥饿
     */
    public static final int timeoutMsecs = 1 * 1000;
    /**
     * 锁超时时间，防止线程在入锁以后，无限的执行等待
     */
    public static final int expireMsecs = 5 * 1000;
}
