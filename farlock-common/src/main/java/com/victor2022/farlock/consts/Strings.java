package com.victor2022.farlock.consts;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午2:54
 * @description: 字符串常量
 */
public class Strings {

    /**
     * @description: 固定锁前缀
     */
    public static final String CONF_LOCK_PREFIX = "lockPrefix";

    /**
     * @description: 配置前缀
     */
    public static final String CONF_PREFIX = "spring.farlock";

    /**
     * @description: 配置文件名
     */
    public static final String CONF_FILENAME = "farlock.properties";

    public static final String TYPE_REDIS = "redis";
    public static final String TYPE_ZK = "zookeeper";
    public static final String TYPE_MYSQL = "mysql";
    // 统一配置
    public static final String CONF_IP = "ip";
    public static final String CONF_PORT = "port";
    public static final String CONF_TYPE = "type";
    public static final String CONF_DEVICE_ID = "id";
    // jedis相关配置
    public static final String CONF_JEDIS_MAX = "jedis.max";
    public static final String CONF_JEDIS_MIN = "jedis.min";
    public static final String CONF_JEDIS_TOTAL = "jedis.total";
    public static final String CONF_JEDIS_TIMEOUT = "jedis.timeout";
    public static final String CONF_LOCK_TIMEOUT = "lock.timeout";


}
