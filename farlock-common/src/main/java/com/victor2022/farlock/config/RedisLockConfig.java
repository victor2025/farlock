package com.victor2022.farlock.config;

import com.victor2022.farlock.consts.Defaults;
import com.victor2022.farlock.consts.Strings;

/**
 * @author: victor2022
 * @date: 2022/7/27 下午10:43
 * @description: 基于redis的锁配置
 */
public class RedisLockConfig extends BasicConfig {

    private static volatile Long lockTimeout;
    private static volatile String lockPrefix;

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/27 下午10:54
     * @description: 锁过期时间
     */
    public static long getLockTimeout() {
        if(lockTimeout==null){
            synchronized (RedisLockConfig.class){
                if(lockTimeout==null){
                    String lockTimeoutStr = properties.getProperty(Strings.CONF_LOCK_TIMEOUT, Defaults.DEF_LOCK_TIMEOUT);
                    lockTimeout = Long.valueOf(lockTimeoutStr);
                }
            }
        }
        return lockTimeout;
    }

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/27 下午10:53
     * @description: Redis中的锁前缀
     */
    public static String getLockPrefix() {
        if(lockPrefix==null){
            synchronized (RedisLockConfig.class){
                if(lockPrefix==null){
                    lockPrefix = properties.getProperty(Strings.CONF_LOCK_PREFIX,Defaults.DEF_LOCK_PREFIX);
                }
            }
        }
        return lockPrefix;
    }
}
