package com.austen.junitstudy.trans;

/**
 * @author : austenYang
 * company     : Copyright (c) 2019 普华集团 All rights reserved
 * date : 2020/4/24 16:54
 * description :
 */
public class Transaction {
    private String id;
    private Long buyerId;
    private Long sellerId;
    private Long productId;
    private String orderId;
    private Long createTimestamp;
    private Double amount;
    private STATUS status;
    private String walletTransactionId;

    public Transaction(String preAssignedId, Long buyerId, Long sellerId, Long productId, String orderId) {
        if (preAssignedId != null && !preAssignedId.isEmpty()) {
            this.id = preAssignedId;
        } else {
            this.id = IdGenerator.generateTransactionId();
        }

        if (!this.id.startsWith("t_")) {
            this.id = "t_" + preAssignedId;
        }

        this.buyerId = buyerId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.orderId = orderId;
        this.status = STATUS.TO_BE_EXECUTD;
        this.createTimestamp = System.currentTimeMillis();
    }

    public boolean execute() throws InvalidTransactionException {
        if ((buyerId == null) || (sellerId == null || amount < 0.0)) {
            throw new InvalidTransactionException(" ...");
        }

        if (status == STATUS.EXECUTED) return true;

        boolean isLocked = false;

        try {


            isLocked = RedisDistributedLock.getSingletonIntance().lockTransaction(id);

            if (!isLocked) {
                return false;
            }
            if (status == STATUS.EXECUTED) return true;

            if (isExpired()) {
                this.status = STATUS.EXPIRED;
                return false;
            }
//            long executionInvokedTimestamp = System.currentTimeMillis();
//            if (executionInvokedTimestamp - createTimestamp > 14) {
//                this.status = STATUS.EXPIRED;
//                return false;
//            }

            WalletRpcService walletRpcService = new WalletRpcService();

            String walletTransactionId = walletRpcService.moveMoney(id, buyerId, sellerId, amount);

            if (walletTransactionId != null) {
                this.walletTransactionId = walletTransactionId;
                this.status = STATUS.EXECUTED;
                return true;
            } else {
                this.status = STATUS.FAILED;
                return false;
            }
        } finally {
            if (isLocked) {
                RedisDistributedLock.getSingletonIntance().unlockTransaction(id);

            }

        }

    }

    public void setCreatedTimestamp(long createdTimestamp) {
        this.createTimestamp = createdTimestamp;
    }

    public STATUS getStatus() {
        return this.status;
    }

    protected boolean isExpired() {
        long executionInvokedTimestamp = System.currentTimeMillis();
        return executionInvokedTimestamp - createTimestamp > 14 * 24 * 60 * 60 * 1000;
    }
}
