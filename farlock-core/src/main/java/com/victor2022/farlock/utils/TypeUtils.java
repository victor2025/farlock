package com.victor2022.farlock.utils;

import com.victor2022.farlock.consts.Strings;
import com.victor2022.farlock.exceptions.LockException;
import com.victor2022.farlock.locks.redis.RedisLock;

/**
 * @author: victor2022
 * @date: 2022/7/27 上午11:22
 * @description:
 */
public class TypeUtils {

    /**
     * @param centerType:
     * @return: java.lang.Class
     * @author: victor2022
     * @date: 2022/7/27 上午11:26
     * @description: 由centerType获取class对象
     */
    public static Class getLockClassByCenterType(String centerType){
        if(centerType.equals(Strings.TYPE_REDIS)){
            return RedisLock.class;
        }else if(centerType.equals(Strings.TYPE_ZK)){
            return null;
        }else if(centerType.equals(Strings.TYPE_MYSQL)){
            return null;
        }else{
            throw new LockException("Unsupported center type!");
        }
    }

}
