package com.victor2022.farlock.locktest;

import com.victor2022.farlock.factory.FarlockFactory;
import com.victor2022.farlock.locks.Lock;
import com.victor2022.farlock.utils.TimeUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: victor2022
 * @date: 2022/7/28 下午9:52
 * @description: 测试最大加锁解锁频率
 */
@Component
public class LockFrequencyTest implements InitializingBean {

    @Autowired
    private FarlockFactory lockFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        Lock lock = lockFactory.getLock("frequencyLock");
        TimeUtils.Timer timer = TimeUtils.getTimer();
        int cnt = 100000;
        timer.tic();
        for (int i = 0; i < cnt; i++) {
            lock.lock();
            lock.unlock();
        }
        timer.toc();
        System.out.println("Lock frequency test is done...");
    }
}
