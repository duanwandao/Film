package com.zjx.film;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-15 16:27
 */
public class StackOverflowTest {

    /**
     * 堆内存溢出
     */
    @Test
    public void MemoryLeak() {
        String[] s = new String[1000];
        Map<String, Object> m = new HashMap<>();
        int i = 0;
        int j = 1000000;
        while (true) {
            for (; i < j; i++) {
                StackOverflowTest memoryLeak = new StackOverflowTest();
                m.put(String.valueOf(i), memoryLeak);
                System.out.println(String.valueOf(i));
            }
        }
    }
}
