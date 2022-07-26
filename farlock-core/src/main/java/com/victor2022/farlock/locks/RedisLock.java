package com.victor2022.farlock.locks;


/**
 * @author: victor2022
 * @date: 2022/07/26  下午3:05
 * @description: 基于redis的锁抽象类
 */
public abstract class RedisLock implements Lock {

    private ThreadLocal<String> lockId = null;

    /**
     * @return: boolean
     * @author: victor2022
     * @date: 2022/7/26 下午3:09
     * @description: 尝试加锁
     */
    public boolean tryLock(){
        return true;
    }
}
