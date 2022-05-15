package com.example.config;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableApolloConfig(value = "redis")
public class AppConfig {
    @Bean(name = "javaConfigBean")
    public TestJavaConfigBean javaConfigBean() {
        return new TestJavaConfigBean();
    }
}
