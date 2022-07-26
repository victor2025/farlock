package com.victor2022.farlock.locks;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午3:04
 * @description: 锁顶层接口
 */
public interface ILock {

    public void lock();

    public void unlock();
}
