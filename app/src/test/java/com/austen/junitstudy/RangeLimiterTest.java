package com.austen.junitstudy;

import com.austen.junitstudy.study01.RangeLimiter;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author : austenYang
 * company     : Copyright (c) 2019 普华集团 All rights reserved
 * date : 2020/4/24 17:47
 * description :
 */
public class RangeLimiterTest {
    @Test
    public void testMove_betweenRange(){
        RangeLimiter rangeLimiter = new RangeLimiter();
        assertTrue(rangeLimiter.move(1));
        assertTrue(rangeLimiter.move(3));
        assertTrue(rangeLimiter.move(-5));


    }

    @Test
    public void testMove_exceedRange(){
        RangeLimiter rangeLimiter = new RangeLimiter();
        assertFalse(rangeLimiter.move(6));
    }
}
