package com.test.jta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestController2 {

    @Autowired
    private TestController testController;

    public void testInSt() throws Exception {
        testController.testInsertOne();
    }
}
