package com.victor2022.farlock.utils;


/**
 * @author: victor2022
 * @date: 2022/1/28 下午1:29
 * @description:
 */
public class TimeUtils {

    public static Timer getTimer(){
        return new Timer();
    }
    public static class Timer {
        private long tic = 0;
        private long toc = 0;

        public void tic(){
            tic = System.currentTimeMillis();
        }

        public void toc(){
            toc = System.currentTimeMillis()-tic;
            System.out.println("当前时间已过"+toc+"毫秒");
        }
    }
}


