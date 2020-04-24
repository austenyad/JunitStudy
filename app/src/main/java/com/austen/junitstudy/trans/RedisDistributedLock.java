package com.austen.junitstudy.trans;

/**
 * @author : austenYang
 * company     : Copyright (c) 2019 普华集团 All rights reserved
 * date : 2020/4/24 17:39
 * description :
 */
public class RedisDistributedLock {

    public static RedisDistributedLock getSingletonIntance() {
        return new RedisDistributedLock();
    }


    public boolean lockTransaction(String id) {
        return true;
    }


    public void unlockTransaction(String id) {

    }
}
