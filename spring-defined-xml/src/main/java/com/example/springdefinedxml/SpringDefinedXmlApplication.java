package com.example.springdefinedxml;


import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author izgnod
 */
public class SpringDefinedXmlApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/fool-application.xml");
        ApplicationConfig applicationConfig = context.getBean(ApplicationConfig.class);
        System.out.println(applicationConfig.getName());
        ServiceConfig serviceConfig = context.getBean(ServiceConfig.class);
        System.out.println(serviceConfig.getName());
    }
}
