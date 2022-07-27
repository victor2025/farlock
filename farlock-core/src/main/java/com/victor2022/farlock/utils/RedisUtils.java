package com.victor2022.farlock.utils;

import com.victor2022.farlock.pool.JedisPoolFactory;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

/**
 * @author: victor2022
 * @date: 2022/7/27 下午10:23
 * @description: redis通信工具类
 */
public class RedisUtils {


    /**
     * @param key:
     * @param id:
     * @return: boolean
     * @author: victor2022
     * @date: 2022/7/27 下午10:24
     * @description: 通知redis加锁
     */
    public static boolean callForLock(String key, String id, Long expire) {
        Jedis jedis = JedisPoolFactory.getPool().getResource();
        Long res = (Long) jedis.eval(lockScript, 1, key, id, expire.toString());
        return res > 0;
    }

    public static boolean callForUnlock(String key, String id) {
        Jedis jedis = JedisPoolFactory.getPool().getResource();
        Long res = (Long) jedis.eval(unlockScript, 1, key, id);
        return res > 0;
    }


    /**
     * @param null:
     * @return: null
     * @author: victor2022
     * @date: 2022/7/27 下午10:34
     * @description: 加锁lua脚本
     */
    private static final String lockScript =
            "if (redis.call('exists', KEYS[1]) == 0) then " +
                    "redis.call('hset', KEYS[1], ARGV[1], 1); " +
                    "redis.call('pexpire', KEYS[1], ARGV[2]); " +
                    "elseif (redis.call('hexists', KEYS[1], ARGV[1]) == 1) then " +
                    "redis.call('hincrby', KEYS[1], ARGV[1], 1); " +
                    "redis.call('pexpire', KEYS[1], ARGV[2]); " +
                    "end; " +
                    "return redis.call('pttl', KEYS[1]);";
    private static final String unlockScript =
            "if (redis.call('hexists', KEYS[1], ARGV[1]) == 1) then " +
                    "if (redis.call('hget', KEYS[1], ARGV[1]) == '1') then " +
                    "return redis.call('hdel', KEYS[1], ARGV[1]); " +
                    "else return redis.call('hincrby', KEYS[1], ARGV[1],-1); " +
                    "end; " +
                    "end; " +
                    "return 1;";

    public static void main(String[] args) {
        System.out.println(callForLock("testlock", "victor2022", 30000l));
    }
}
