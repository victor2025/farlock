package com.victor2022.farlock.utils;

import com.victor2022.farlock.config.ConfigHolder;

import java.util.Random;
import java.util.UUID;

/**
 * @author: victor2022
 * @date: 2022/7/27 上午12:05
 * @description:
 */
public class IDUtils {

    private static SnowflakeIdWorker snowflakeIdWorker = null;
    private static Random random = new Random();

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
     * @param length:
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/27 上午10:34
     * @description: 获取指定长度的long
     */
    public static long getRandomLong(int length){
        long res = 0;
        for(int i = 0; i<length; i++){
            res+=res*10+random.nextInt(10);
        }
        return res;
    }

    /**
     * @param bound:
     * @return: int
     * @author: victor2022
     * @date: 2022/7/27 上午10:57
     * @description: 获取指定大小内的随机数
     */
    public static int getRandomInt(int bound){
        return random.nextInt(bound);
    }
    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/27 上午12:27
     * @description: 获取雪花算法id
     */
    public static String getSnowFlakeID() {
        if(snowflakeIdWorker==null){
            synchronized (IDUtils.class){
                if(snowflakeIdWorker==null){
                    snowflakeIdWorker = new SnowflakeIdWorker(ConfigHolder.getConfig().getDeviceId(), 0);
                }
            }
        }
        return String.valueOf(snowflakeIdWorker.nextId());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(getUUID());
            System.out.println(getSnowFlakeID());
        }
    }
}
