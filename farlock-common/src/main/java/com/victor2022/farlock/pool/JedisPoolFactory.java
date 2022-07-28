package com.victor2022.farlock.pool;

import com.victor2022.farlock.config.loaders.JedisConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: victor2022
 * @date: 2022/07/26  上午11:17
 * @description: 获取Jedis连接池
 */
public class JedisPoolFactory {

    private static Logger logger = LoggerFactory.getLogger(JedisPoolFactory.class);

    // 单例连接池
    public volatile static JedisPool pool = null;

    /**
     * @return: redis.clients.jedis.JedisPool
     * @author: victor2022
     * @date: 2022/7/26 下午4:17
     * @description: 获取redis连接池 单例
     */
    public static JedisPool getPool() {
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
    private static JedisPool initPool(){
        // 获取poolConfig
        JedisPoolConfig poolConfig = JedisConfigLoader.getPoolConfig();
        // 创建JedisPool
        JedisPool pool = new JedisPool(poolConfig,JedisConfigLoader.getIp(),JedisConfigLoader.getPort(),JedisConfigLoader.getTimeout());
        logger.info("Initializing Jedis pool at "+JedisConfigLoader.getIp()+":"+JedisConfigLoader.getPort());
        return pool;
    }

}
