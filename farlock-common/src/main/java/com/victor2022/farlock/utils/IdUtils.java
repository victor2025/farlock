package com.victor2022.farlock.utils;

import java.util.UUID;

/**
 * @author: victor2022
 * @date: 2022/7/27 上午12:05
 * @description:
 */
public class IdUtils {

    private static SnowflakeIdWorker snowflakeIdWorker = null;

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/27 上午12:15
     * @description: 获取UUID
     */
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/27 上午12:27
     * @description: 获取雪花算法id
     */
    public static String getSnowFlakeID(){
        if(snowflakeIdWorker==null){
            synchronized (IdUtils.class){
                if(snowflakeIdWorker==null){
                    snowflakeIdWorker = new SnowflakeIdWorker(0,0);
                }
            }
        }
        return String.valueOf(snowflakeIdWorker.nextId());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getUUID());
        }
    }
}
