package com.zjx.test;

/**
 * @Description
 * 线程栈溢出（java.lang.StackOverflowError）
 *
 *        线程栈时线程独有的一块内存结构，所以线程栈发生问题必定是某个线程运行时产生的错误。
 *
 *        一般线程栈溢出是由于递归太深或方法调用层级过多导致的。
 *
 *        发生栈溢出的错误信息为：
 *
 *               java.lang.StackOverflowError
 * @auther 断弯刀
 * @create 2019-05-15 16:55
 */
public class StackOverflowTest {

    public static void main(String[] args) {
        int i =0;
        digui(i);
    }

    private static void digui(int i){
        System.out.println(i++);
        String[] s = new String[50];
        digui(i);
    }

}
