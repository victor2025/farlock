package com.victor2022.farlock.pool;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午3:44
 * @description:
 */
public class JedisPoolTest {

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(100);
        config.setMinIdle(10);
        config.setMaxTotal(1000);
        // 创建连接池
        JedisPool pool = new JedisPool(config, "localhost", 6379);
        Jedis conn = pool.getResource();
        conn.set("test", String.valueOf(1));
        System.out.println(conn.get("test"));
    }
}
