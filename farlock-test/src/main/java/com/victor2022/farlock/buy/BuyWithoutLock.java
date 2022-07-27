package com.victor2022.farlock.buy;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: victor2022
 * @date: 2022/7/27 下午11:48
 * @description: 无锁情况
 */
public class BuyWithoutLock {

    private static int cnt = 10000;
    private static Set<Integer> set = new HashSet<>();


    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (cnt > 0) {
                    if(set.contains(cnt)){
                        System.out.println("HIT!!!->"+cnt);
                    }else{
                        set.add(cnt);
                    }
                    cnt--;
                }
            }
        };
        new Thread(runnable).start();
        new Thread(runnable).start();
        System.out.println("done");
    }

}
