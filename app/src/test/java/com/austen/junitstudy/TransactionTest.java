package com.austen.junitstudy;

import com.austen.junitstudy.trans.STATUS;
import com.austen.junitstudy.trans.Transaction;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author : austenYang
 * company     : Copyright (c) 2019 普华集团 All rights reserved
 * date : 2020/4/24 18:55
 * description :
 *
 *
 *
 * 提高代码可测试性的方式：
 * 1. 未决行为
 * 2. 全局变量
 * 3. 静态方法
 * 4. 复杂继承
 * 5. 高耦合代码
 *
 *
 *  依赖注入是提高代码可测试性的最主要手段。所以，依赖注入就是不在类内部通过 new 的方式创建对象，而是通过外部创建
 *  好之后传递给类对象使用。那是不是所有对象都不能在类内部创建呢？那种类型的对象可以在类内部创建并且不会影响代码的
 *  可测试性？
 *  答：提供方法的类不要 new ，也就是我们常说的 service 类，这些类可以依赖注入。提供属性的类比如 vo,bo,entity 这些可以 new。
 *
 *
 */
public class TransactionTest {

    @Test
    public void testExecute_with_TransactionIsExpired() {
        Long buyId = 123L;
        Long seller = 234L;
        Long productId = 345L;
        String orderId = "456";
        Transaction transaction = new Transaction(null, buyId, seller, productId, orderId);
        transaction.setCreatedTimestamp(System.currentTimeMillis() - 14 * 24 * 60 * 60 * 1000); // 14 天
        boolean actualResult = transaction.execute();
        Assert.assertFalse(actualResult);
        Assert.assertEquals(STATUS.EXPIRED, transaction.getStatus());
    }

    @Test
    public void testExecute_with_TransactionIsExpired2() {
        Long buyId = 123L;
        Long seller = 234L;
        Long productId = 345L;
        String orderId = "456";
        Transaction transaction = new Transaction(null, buyId, seller, productId, orderId) {
            @Override
            protected boolean isExpired() {
                return super.isExpired();
            }
        };

        boolean actualResult = transaction.execute();
        Assert.assertFalse(actualResult);
        Assert.assertEquals(STATUS.EXPIRED, transaction.getStatus());

    }
}
