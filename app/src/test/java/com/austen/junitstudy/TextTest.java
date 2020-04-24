package com.austen.junitstudy;


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author : austenYang
 * company     : Copyright (c) 2019 普华集团 All rights reserved
 * date : 2020/4/23 17:17
 * description :
 */
public class TextTest {


    @Test
    public void testToNumber() {
        // ""
        Text text1 = new Text("");
        Integer integer1 = text1.toNumber();
        assertNull(integer1);

        // "123"
        Text text2 = new Text("123");
        Integer integer2 = text2.toNumber();
        assertEquals(123, (long) integer2);

        Text text3 = new Text(null);
        Integer integer3 = text3.toNumber();
        assertNull(integer3);

        Text text4 = new Text(" 123");
        Integer integer4 = text4.toNumber();
        assertEquals(123, (long) integer4);

        Text text5 = new Text(" 1 23");
        Integer integer5 = text5.toNumber();
        assertNull(integer5);

        Text text6 = new Text(" 1a2 3");
        Integer integer6 = text6.toNumber();
        assertNull(integer6);

    }
}
