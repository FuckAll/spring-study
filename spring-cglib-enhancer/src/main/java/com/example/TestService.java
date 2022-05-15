package com.example;

/**
 * @author izgnod
 */
public class TestService {
    public void testA() {
        System.out.println("TestService.testA");
        testB();
    }
    public void testB() {
        System.out.println("TestService.testB");
    }
}
