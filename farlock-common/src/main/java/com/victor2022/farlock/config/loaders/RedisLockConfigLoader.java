package com.victor2022.farlock.config.loaders;

import com.victor2022.farlock.config.ConfigHolder;

/**
 * @author: victor2022
 * @date: 2022/7/27 下午10:43
 * @description: 基于redis的锁配置
 */
public class RedisLockConfigLoader extends ConfigHolder {

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/27 下午10:54
     * @description: 锁过期时间
     */
    public static long getLockTimeout() {
        return config.getLockTimeout();
    }

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/27 下午10:53
     * @description: Redis中的锁前缀
     */
    public static String getLockPrefix() {
        return config.getLockPrefix();
    }
}
