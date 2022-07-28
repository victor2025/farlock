package com.victor2022.farlock.buy;

import com.victor2022.farlock.FarlockFactory;
import com.victor2022.farlock.locks.Lock;
import com.victor2022.farlock.utils.TimeUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: victor2022
 * @date: 2022/7/27 下午11:48
 * @description: 采用分布式锁情况
 */
public class BuyWithFarLock {

    private static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws InterruptedException {
        Goods goods = new Goods();
        Lock lock = FarlockFactory.getFarlockFactory().getLock("myLock");
        boolean[] vis = new boolean[goods.cnt+1];
        // 用于计时
        TimeUtils.Timer timer = TimeUtils.getTimer();
        CountDownLatch latch = new CountDownLatch(5);
        // 创建线程
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(goods.getCnt()>0){
                    // 加锁
                    lock.lock();
                    // 扣减库存
                    int cnt = goods.decr();
                    if(vis[cnt]){
                        System.out.println("HIT!!!->"+cnt);
                    }else{
                        vis[cnt] = true;
                    }
                    // 解锁
                    lock.unlock();
                }
                latch.countDown();
            }
        };
        timer.tic();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        // 等待所有线程结束
        latch.await();
        timer.toc();
        System.out.println("done");
    }

    public static class Goods {

        private int cnt = 100000;

        public int decr(){
            if(cnt>0)cnt--;
            return cnt;
        }

        public int getCnt(){
            return cnt;
        }
    }

}
