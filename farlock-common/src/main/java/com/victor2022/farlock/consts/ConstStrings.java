package com.victor2022.farlock.consts;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午2:54
 * @description: 字符串常量
 */
public class ConstStrings {

    /**
     * @description: 固定锁前缀
     */
    public static final String LOCK_PREFIX = "farlock";

    /**
     * @description: 配置前缀
     */
    public static final String CONF_PREFIX = "farlock";

    /**
     * @description: 配置文件名
     */
    public static final String CONF_FILENAME = "farlock.properties";

    // 统一配置
    public static final String CONF_IP = "ip";
    public static final String CONF_PORT = "port";
    // jedis相关配置
    public static final String CONF_JEDIS_MAX = "jedis.max";
    public static final String CONF_JEDIS_MIN = "jedis.min";
    public static final String CONF_JEDIS_TOTAL = "jedis.total";
    public static final String CONF_JEDIS_TIMEOUT = "jedis.timeout";
    public static final String CONF_LOCK_TIMEOUT = "lock.timeout";


}
