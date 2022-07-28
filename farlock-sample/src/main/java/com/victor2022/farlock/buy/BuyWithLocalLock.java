package com.victor2022.farlock.buy;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: victor2022
 * @date: 2022/7/27 下午11:48
 * @description: 采用本地锁情况
 */
public class BuyWithLocalLock {

    private static Set<Integer> set = new HashSet<>();


    public static void main(String[] args) {
        Goods goods = new Goods();
        ReentrantLock lock = new ReentrantLock();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(goods.getCnt()>0){
                    // 加锁
                    lock.lock();
                    // 扣减库存
                    int cnt = goods.decr();
                    if(set.contains(cnt)){
                        System.out.println("HIT!!!->"+cnt);
                    }else{
                        set.add(cnt);
                    }
                    // 解锁
                    lock.unlock();
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        System.out.println("done");
    }

    public static class Goods {

        private int cnt = 10000;

        public int decr(){
            cnt--;
            return cnt;
        }

        public int getCnt(){
            return cnt;
        }
    }

}
