package com.victor2022.farlock.locks.redis;

import com.victor2022.farlock.pool.JedisPoolFactory;
import redis.clients.jedis.Jedis;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: victor2022
 * @date: 2022/7/27 下午10:23
 * @description: redis通信工具类
 */
public class RedisLockHandler {

    // 用于处理jedis线程安全问题
    private static ReentrantLock lock = new ReentrantLock();

    /**
     * @param key:
     * @param id:
     * @return: boolean
     * @author: victor2022
     * @date: 2022/7/27 下午10:24
     * @description: 通知redis加锁
     * 会出现线程安全问题，因此需要加锁
     */
    public static boolean callForLock(String key, String id, Long expire) {
        Long res = (Long) syncEval(lockScript, 1, key, id, expire.toString());
        return res > 0;
    }

    /**
     * @param key:
     * @param id:
     * @return: boolean
     * @author: victor2022
     * @date: 2022/7/28 上午10:52
     * @description: 通知redis解锁
     */
    public static boolean callForUnlock(String key, String id) {
        Long res = (Long) asyncEval(unlockScript, 1, key, id);
        return res > 0;
    }

    /**
     * @param script:
     * @param keyCnt:
     * @param params:
     * @return: java.lang.Object
     * @author: victor2022
     * @date: 2022/7/28 上午11:55
     * @description: 同步执行lua脚本
     */
    private static Object syncEval(String script, int keyCnt, String... params){
        Jedis jedis = JedisPoolFactory.getPool().getResource();
        Object res = null;
        lock.lock();
        try{
            res = jedis.eval(script, keyCnt, params);
        }finally {
            releaseResource(jedis);
            lock.unlock();
        }
        return res;
    }

    /**
     * @param script:
     * @param keyCnt:
     * @param params:
     * @return: java.lang.Object
     * @author: victor2022
     * @date: 2022/7/28 上午11:56
     * @description: 非同步执行lua脚本
     */
    private static Object asyncEval(String script, int keyCnt, String... params){
        Jedis jedis = JedisPoolFactory.getPool().getResource();
        Object res = jedis.eval(script, keyCnt, params);
        releaseResource(jedis);
        return res;
    }

    /**
     * @param jedis:
     * @return: void
     * @author: victor2022
     * @date: 2022/7/28 上午10:53
     * @description: 释放连接资源
     */
    private static void releaseResource(Jedis jedis){
        if(jedis!=null){
            jedis.close();
        }
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
