package com.test.transactionmq.controller;

import com.test.transactionmq.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxiangdong
 */
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/createOrder")
    public String createOrder(String orderNo) {
        orderService.createOrder(orderNo);
        return "ok";
    }
}
