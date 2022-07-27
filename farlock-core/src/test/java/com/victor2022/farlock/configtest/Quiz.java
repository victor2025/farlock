package com.victor2022.farlock.configtest;

/**
 * @author: victor2022
 * @date: 2022/7/27 下午8:50
 * @description:
 */
public class Quiz {

    public static void main(String[] args) {
        System.out.println(solution(2,1,3,1,4,1,9));
    }

    private static int solution(int a, int b, int c, int d, int e, int f, int z){
        double max1 = Math.max(a,Math.max(b,c));
        double min1 = Math.min(a,Math.min(b,c));
        double max2 = Math.max(d,Math.max(e,f));
        double min2 = Math.min(d,Math.min(e,f));
        // 计算x的上下界
        int max = (int)(Math.pow((double)z/(3.0*min1),1.0/min2))+1;
        int min = (int)(Math.pow((double)z/(3.0*max1),1.0/max2));
        int left = min, right = max;
        while(left<right){
            int mid = left+(right-left)/2;
            int curr = calc(a,b,c,d,e,f,mid);
            if(curr<z){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }

    private static int calc(int a, int b, int c, int d, int e, int f, int x){
        return (int)(a*Math.pow(x,b)+c*Math.pow(x,d)+e*Math.pow(x,f));
    }
}
