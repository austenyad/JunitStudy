package com.austen.junitstudy.study01;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : austenYang
 * company     : Copyright (c) 2019 普华集团 All rights reserved
 * date : 2020/4/24 17:43
 * description :
 */
public class RangeLimiter {
    private static AtomicInteger position = new AtomicInteger(0);

    public static final int MAX_LIMIT = 5;
    public static final int MIN_LIMIT = -5;

    public boolean move(int delta) {
        int currentPos = position.addAndGet(delta);
        System.out.println(currentPos);
        boolean betweenRange = (currentPos <= MAX_LIMIT) && (currentPos >= MIN_LIMIT);
        return betweenRange;
    }


}
