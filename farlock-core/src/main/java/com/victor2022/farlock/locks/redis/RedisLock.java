package com.victor2022.farlock.locks.redis;


import com.victor2022.farlock.config.RedisLockConfig;
import com.victor2022.farlock.consts.Defaults;
import com.victor2022.farlock.locks.Lock;
import com.victor2022.farlock.utils.IDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午3:05
 * @description: 基于redis的锁抽象类
 */
public class RedisLock implements Lock {

    private volatile String lockName;
    private volatile String keyName;
    private Map<Thread,String> idMap = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(RedisLock.class);

    public RedisLock(String lockName){
        initLock(lockName);
    }

    private void initLock(String lockName){
        this.lockName = lockName;
        this.keyName = RedisLockConfig.getLockPrefix() +":"+lockName;
    }

    /**
     * @return: boolean
     * @author: victor2022
     * @date: 2022/7/26 下午3:09
     * @description: 尝试加锁
     */
    private boolean tryLock(String id){
        return RedisLockHandler.callForLock(this.keyName,id,RedisLockConfig.getLockTimeout());
    }

    /**
     * @param id:
     * @param timeout:
     * @return: boolean
     * @author: victor2022
     * @date: 2022/7/27 下午11:49
     * @description: 带超时加锁
     */
    private boolean tryLock(String id, long timeout){
        return RedisLockHandler.callForLock(this.keyName,id,timeout);
    }

    /**
     * @param id:
     * @return: boolean
     * @author: victor2022
     * @date: 2022/7/27 下午11:49
     * @description: 尝试解锁
     */
    private boolean tryUnlock(String id){
        return RedisLockHandler.callForUnlock(this.keyName,id);
    }

    /**
     * @return: java.lang.String
     * @author: victor2022
     * @date: 2022/7/28 上午10:21
     * @description: 获取线程的锁id
     */
    private String getLockId(){
        Thread t = Thread.currentThread();
        String id = idMap.get(t);
        if(id==null){
            id = IDUtils.getSnowFlakeID();
            idMap.put(t,id);
        }
        return id;
    }

    /**
     * @return: void
     * @author: victor2022
     * @date: 2022/7/28 上午12:37
     * @description: 无自定义超时时间的自旋加锁
     */
    @Override
    public void lock() {
        String id = getLockId();
        // 自旋加锁
        while(!tryLock(id)){}
    }

    /**
     * @param timeout:
     * @return: void
     * @author: victor2022
     * @date: 2022/7/28 上午12:37
     * @description: 自定义超时时间的自旋解锁
     */
    @Override
    public void lock(long timeout) {
        String id = getLockId();
        if(timeout<=1000){
            logger.warn("Lock expire time is too short, use default value: "+ Defaults.DEF_LOCK_TIMEOUT);
            lock();
        }else{
            while(!tryLock(id,timeout)){}
        }
    }

    /**
     * @return: void
     * @author: victor2022
     * @date: 2022/7/28 上午12:36
     * @description: 循环解锁
     */
    @Override
    public void unlock() {
        String id = getLockId();
        while(!tryUnlock(id));
    }
}
