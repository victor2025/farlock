package com.victor2022.farlock.consts;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午4:47
 * @description: 默认参数
 */
public class Defaults {

    public static final String DEF_IP = "127.0.0.1";
    // jedis相关
    public static final String DEF_JEDIS_PORT = "6379";
    public static final String DEF_JEDIS_MAX = "100";
    public static final String DEF_JEDIS_MIN = "10";
    public static final String DEF_JEDIS_TOTAL = "1000";
    public static final String DEF_JEDIS_TIMEOUT = "2000";
    // 默认锁过期时间30s
    public static final String DEF_LOCK_TIMEOUT = "30000";


}
