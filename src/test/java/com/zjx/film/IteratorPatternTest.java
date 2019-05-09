package com.zjx.film;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-05-06 8:18
 */
public class IteratorPatternTest {

    @Test
    public void test01(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

     /*   int i=5;
        System.out.println(i);*/
        for (Iterator<String> i = list.iterator(); i.hasNext(); ) {
            String item = i.next();
            System.out.println(item);
            i.remove();
        }
        System.out.println("------------");
        for (String item : list) {
            System.out.println(item);
        }

    }
}
