package com.test.transactionmq.service;

/**
 * @author wuxiangdong
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @param orderId
     */
    void createOrder(String orderId);
}
