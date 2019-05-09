package com.zjx.film;

import org.junit.Test;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-09 13:14
 */
public class EnumTest {

    public enum Signal{
        GREEN,YELLOW, RED
    }

    @Test
    public void test01(){
        Signal color = Signal.RED;
        switch (color) {
            case RED:
                color = Signal.GREEN;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
            case GREEN:
                color = Signal.RED;
                break;
        }
        System.out.println(color);
    }


}
