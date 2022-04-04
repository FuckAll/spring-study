package com.test.transactionmq.service.impl;

import com.test.transactionmq.producer.TransactionProducer;
import com.test.transactionmq.service.OrderService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wuxiangdong
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final TransactionProducer transactionProducer;

    @Autowired
    public OrderServiceImpl(TransactionProducer transactionProducer) {
        this.transactionProducer = transactionProducer;
    }

    @Override
    public void createOrder(String orderId) {
        try {
            transactionProducer.send("topic-test", "order id is 11111211");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
