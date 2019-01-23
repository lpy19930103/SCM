package com.lpy.scm.redis;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisPool implements RedisUtils {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public void set(String key, String value) {
        Jedis resource = jedisPool.getResource();
//        resource.auth("admin");
        resource.set(key, value);
        resource.close();
    }

    @Override
    public String get(String key) {
        Jedis resource = jedisPool.getResource();
//        resource.auth("admin");
        String s = resource.get(key);
        resource.close();
        return s;
    }

    @Override
    public void del(String key) {
        Jedis resource = jedisPool.getResource();
//        resource.auth("admin");
        resource.del(key);
        resource.close();

    }

    @Override
    public void expire(String key, Integer seconds) {
        Jedis resource = jedisPool.getResource();
//        resource.auth("admin");
        resource.expire(key, seconds);
        resource.close();

    }

    @Override
    public void set(String key, String value, Integer seconds) {
        Jedis resource = jedisPool.getResource();
//        resource.auth("admin");
        resource.set(key, value);
        resource.expire(key, seconds);
        resource.close();
    }

    @Override
    public Long incr(String key) {
        Jedis resource = jedisPool.getResource();
//        resource.auth("admin");
        Long incr = resource.incr(key);
        resource.close();
        return incr;
    }
}
