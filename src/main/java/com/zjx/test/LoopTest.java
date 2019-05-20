package com.zjx.test;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-19 9:36
 */
public class LoopTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("1111=="+System.currentTimeMillis());

        spendTime();
        long end = System.currentTimeMillis();
        System.out.println("2222=="+(System.currentTimeMillis()-start));


    }

    private static void spendTime() {
        for (int i =3900;i>0;i--) {
            System.out.println(i);
        }

    }
}
