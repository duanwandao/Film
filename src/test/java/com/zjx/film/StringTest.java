package com.zjx.film;

import org.junit.Test;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-04 9:30
 */
public class StringTest {

    /**
     * 拼接字符串常量
     *
     * 使用 加号 进行字符串常量的拼接在编译时就已经完成，而使用 StringBuilder 进行字符串拼接需要在运行时完成。
     * 所以单纯的字符串常量拼接 加号 的效率 应该高于 StringBuilder，
     */
    @Test
    public void String(){
        String t1 = "a" + "b" + "c";
        String t2 = new StringBuilder().append("a").append("b").append("c").toString();

        long l1 = System.currentTimeMillis();
        for (int i=0; i<1_000_000; i++) {
            String s1 = "a" + "b" + "c";
        }
        System.out.println(System.currentTimeMillis() - l1);    //结果： 2

        long l2 = System.currentTimeMillis();
        for (int i=0; i<1_000_000; i++) {
            String s2 = new StringBuilder().append("a").append("b").append("c").toString();
        }
        System.out.println(System.currentTimeMillis() - l2);//结果 ： 35
    }

    /**
     * 拼接字符串与引用
     */
    @Test
    public void String2(){
        String t1 = "a";
        String t2 = new StringBuilder().append(t1).append("b").append("c").toString();
        String t3 = t1 + "b" + "c";

        String s1 = "a";
        long l1 = System.currentTimeMillis();
        for (int i=0; i<1_000_000; i++) {
            String s2 = new StringBuilder().append(s1).append("b").append("c").toString();
        }
        System.out.println(System.currentTimeMillis() - l1);    //结果： 83

        long l2 = System.currentTimeMillis();
        for (int i=0; i<1_000_000; i++) {
            String s3 = s1 + "b" + "c";
        }
        System.out.println(System.currentTimeMillis() - l2);//结果 ： 18
    }

    /**
     * 循环拼接字符串
     *
     *  测试结果 StringBuilder拼接字符串 的效率 远远高于 加号拼接字符串，
     *  主要原因在于 加号拼接字符串的方式 每次循环开始时都会创建一个 StringBuilder实例
     *  而 StringBuilder拼接字符串 的方式只在循环开始前创建了一个 StringBuilder实例。
     */
    @Test
    public void String3(){
        String t1 = "a";
        String t2 = new StringBuilder().append(t1).append("b").append("c").toString();
        String t3 = t1 + "b" + "c";

        String s1 = "";
        long l1 = System.currentTimeMillis();
        for (int i=0; i<100_000; i++) {
            s1 = s1 + "a" + "b" + "c" + "d" + "e" + "f";
        }
        System.out.println((System.currentTimeMillis() - l1) + " " + s1.length());    //结果： 42371 600000

        StringBuilder sb = new StringBuilder();
        String s2;
        long l2 = System.currentTimeMillis();
        for (int i=0; i<100_000; i++) {
            sb.append("a").append("b").append("c").append("d").append("e").append("f");
        }
        s2 = sb.toString();
        System.out.println((System.currentTimeMillis() - l2) + " " + s2.length());//结果 ： 8 600000
    }

    /**
     * StringBuilder、StringBuffer
     */
    @Test
    public void String4(){
       /* StringBuilder sb = new StringBuilder();
        String s2;
        long l1 = System.currentTimeMillis();
        for (int i=0; i<1000_000; i++) {
            sb.append("a").append("b").append("c").append("d").append("e").append("f");
        }
        s2 = sb.toString();
        System.out.println((System.currentTimeMillis() - l1) + " " + s2.length());//结果 ： 108 6000000


        StringBuffer sb2 = new StringBuffer();
        String s3;
        long l2 = System.currentTimeMillis();
        for (int i=0; i<1000_000; i++) {
            sb2.append("a").append("b").append("c").append("d").append("e").append("f");
        }
        s3 = sb.toString();
        System.out.println((System.currentTimeMillis() - l2) + " " + s3.length());//结果 ： 147 6000000*/


        String s1 = "a";
        long l1 = System.currentTimeMillis();
        for (int i=0; i<1_00000_000; i++) {
            String s2 = new StringBuilder().append(s1).append("b").append("c").toString();
        }
        System.out.println(System.currentTimeMillis() - l1);    //结果： 1826

        long l2 = System.currentTimeMillis();
        for (int i=0; i<1_00000_000; i++) {
            String s2 = new StringBuffer().append(s1).append("b").append("c").toString();
        }
        System.out.println(System.currentTimeMillis() - l2);//结果 ： 1133
    }
}
