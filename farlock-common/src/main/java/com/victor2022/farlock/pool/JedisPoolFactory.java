package com.victor2022.farlock.pool;

import com.victor2022.farlock.config.JedisConfigLoader;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: victor2022
 * @date: 2022/07/26  上午11:17
 * @description: 获取Jedis连接池
 */
public class JedisPoolFactory {

    // 单例连接池
    public volatile static JedisPool pool = null;

    /**
     * @return: redis.clients.jedis.JedisPool
     * @author: victor2022
     * @date: 2022/7/26 下午4:17
     * @description: 获取redis连接池 单例
     */
    public JedisPool getPool() {
        if(pool==null){
            synchronized (JedisPoolFactory.class){
                if(pool==null){
                    pool = initPool();
                }
            }
        }
        return pool;
    }

    /**
     * @return: com.victor2022.farlock.pool.IPool
     * @author: victor2022
     * @date: 2022/7/26 上午11:23
     * @description: 根据配置创建连接池
     */
    private JedisPool initPool(){
        // 创建loader
        JedisConfigLoader loader = new JedisConfigLoader();
        // 获取poolConfig
        JedisPoolConfig poolConfig = loader.getPoolConfig();
        // 创建JedisPool
        JedisPool pool = new JedisPool(poolConfig,loader.getIp(),loader.getPort(),loader.getTimeout());
        return pool;
    }

}
