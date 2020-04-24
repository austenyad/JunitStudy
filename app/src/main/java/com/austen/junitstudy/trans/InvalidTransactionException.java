package com.austen.junitstudy.trans;

/**
 * @author : austenYang
 * company     : Copyright (c) 2019 普华集团 All rights reserved
 * date : 2020/4/24 17:04
 * description :
 */
public class InvalidTransactionException extends RuntimeException {
    public InvalidTransactionException(String message) {
        super(message);
    }
}
