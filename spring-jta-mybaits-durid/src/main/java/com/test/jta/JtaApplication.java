package com.test.jta;


import com.test.jta.controller.TestController;
import com.test.jta.service.TestJtaService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author wuxiangdong
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class JtaApplication {

    public static void main(String[] args) throws Exception {
        final ConfigurableApplicationContext run = SpringApplication.run(JtaApplication.class, args);
//        final TestController testController = (TestController) run.getBean("testController");
//        testController.testInsertOne();
        final TestJtaService bean = run.getBean(TestJtaService.class);
        bean.testInsertUser(null);

    }
}
