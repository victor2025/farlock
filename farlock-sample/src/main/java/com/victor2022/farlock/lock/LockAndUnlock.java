package com.victor2022.farlock.lock;

import com.victor2022.farlock.factory.FarlockFactory;
import com.victor2022.farlock.locks.Lock;

/**
 * @author: victor2022
 * @date: 2022/07/28  上午10:08
 * @description: 测试加锁和解锁
 */
public class LockAndUnlock {

    public static void main(String[] args) {
        Lock myLock = FarlockFactory.getLockFactory().getLock("myLock");
        for(int i = 0; i<10; i++){
            myLock.lock(1000000l);
        }

        for (int i = 0; i < 10; i++) {
            myLock.unlock();
        }
    }
}
