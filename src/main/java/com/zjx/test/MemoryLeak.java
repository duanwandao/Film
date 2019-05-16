package com.zjx.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 堆内存溢出
 * 堆中的内存是用来生成对象实例和数组的。
 * @auther 断弯刀
 * @create 2019-05-15 16:44
 */
public class MemoryLeak {

    private String[] s = new String[1000];

    public static void main(String[] args) {
        Map<String,Object> m = new HashMap<>();
        int i =0;
        int j=1000000;
        while(true){
            for(;i<j;i++){
                MemoryLeak memoryLeak = new MemoryLeak();
                m.put(String.valueOf(i), memoryLeak);
                System.out.println(String.valueOf(i));
            }
        }
    }
}
