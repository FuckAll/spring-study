package com.test.jta;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author wuxiangdong
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JtaApplication {

    public static void main(String[] args) {
        SpringApplication.run(JtaApplication.class, args);
    }
}
