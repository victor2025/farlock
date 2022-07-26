package com.victor2022.farlock;

import com.victor2022.farlock.config.JedisConfigLoader;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午5:08
 * @description:
 */
public class TestConfig {

    public static void main(String[] args) {
        JedisConfigLoader jedisConfigLoader = new JedisConfigLoader();
        JedisPool jedisPool = new JedisPool(jedisConfigLoader.getPoolConfig(), jedisConfigLoader.getIp(), jedisConfigLoader.getPort());
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.get("test"));
        System.out.println("done");
    }
}
