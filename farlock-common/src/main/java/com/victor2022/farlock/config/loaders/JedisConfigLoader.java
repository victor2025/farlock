package com.victor2022.farlock.config.loaders;

import com.victor2022.farlock.config.ConfigHolder;
import com.victor2022.farlock.consts.Strings;
import com.victor2022.farlock.consts.Defaults;
import com.victor2022.farlock.exceptions.ConfigureException;
import lombok.Data;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: victor2022
 * @date: 2022/07/26  上午11:27
 * @description: jedis的
 */
public class JedisConfigLoader extends ConfigHolder {

    /**
     * @return: redis.clients.jedis.JedisPoolConfig
     * @author: victor2022
     * @date: 2022/7/26 下午4:38
     * @description: 获取连接池配置
     */
    public static JedisPoolConfig getPoolConfig(){
        // 创建并设置config
        JedisPoolConfig jedisConfig = new JedisPoolConfig();
        jedisConfig.setMaxTotal(config.getMaxTotal());
        jedisConfig.setMinIdle(config.getMinIdle());
        jedisConfig.setMaxIdle(config.getMaxIdle());
        // 返回
        return jedisConfig;
    }

    public static String getIp() {
        return config.getIp();
    }

    public static int getPort() {
        return config.getPort();
    }

    public static int getTimeout(){
        return config.getTimeout();
    }
}
