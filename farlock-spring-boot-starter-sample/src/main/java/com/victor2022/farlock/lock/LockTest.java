package com.victor2022.farlock.lock;

import com.victor2022.farlock.factory.FarlockFactory;
import com.victor2022.farlock.locks.Lock;
import com.victor2022.farlock.utils.TimeUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author: victor2022
 * @date: 2022/07/28  下午3:30
 * @description:
 */
@Component
public class LockTest implements InitializingBean {

    @Autowired
    private FarlockFactory lockFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        Goods goods = new Goods();
        Lock lock = lockFactory.getLock("myLock");
        boolean[] vis = new boolean[goods.cnt + 1];
        // 用于计时
        TimeUtils.Timer timer = TimeUtils.getTimer();
        CountDownLatch latch = new CountDownLatch(5);
        // 创建线程
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (goods.getCnt() > 0) {
                    // 加锁
                    lock.lock();
                    timer.toc();
                    // 扣减库存
                    int cnt = goods.decr();
                    if (vis[cnt]) {
                        System.out.println("HIT!!!->" + cnt);
                    } else {
                        vis[cnt] = true;
                    }
                    // 解锁
                    lock.unlock();
                }
                latch.countDown();
            }
        };
        timer.tic();
        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
        // 等待所有线程结束
        latch.await();
        timer.toc();
        System.out.println("done");
    }

    class Goods {
        public int cnt = 100000;

        public int decr() {
            if (cnt > 0) cnt--;
            return cnt;
        }

        public int getCnt() {
            return cnt;
        }
    }
}
