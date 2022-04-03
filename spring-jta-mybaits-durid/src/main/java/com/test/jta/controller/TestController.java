package com.test.jta.controller;

import com.test.jta.pojo.User;
import com.test.jta.service.TestJtaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestJtaService testJtaService;

    @GetMapping("testInsertOne")
    @Transactional
    public String testInsertOne() throws Exception {
        User user = new User();
        user.setUsername("zhangsan");
        user.setAge(11);
        try {
            testJtaService.testInsertUser(user);
        } catch (Exception e) {
            System.out.println("触发时间回滚 " + e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return "ok";
    }

    @GetMapping("testInsertTwo")
    @Transactional
    public String testInsertTwo() throws Exception {
        User user = new User();
        user.setUsername("lisi");
        user.setAge(11);
//        try {
        testJtaService.testInsertUser2(user);
        int a = 100 / 0;
//        } catch (Exception e) {
//            System.out.println("触发时间回滚 " + e.toString());
//            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
        return "ok";
    }

}
