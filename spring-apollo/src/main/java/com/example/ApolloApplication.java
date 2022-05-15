package com.example;

import com.ctrip.framework.apollo.core.ConfigConsts;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import com.example.config.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.MutablePropertySources;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 *
 * @author izgnod
 */
@SpringBootApplication
public class ApolloApplication {
    public static void main( String[] args ) {
        ConfigurableApplicationContext context = SpringApplication.run(ApolloApplication.class, args);

        while (true) {
//            SampleRedisConfig bean = context.getBean("sampleRedisConfig",SampleRedisConfig.class);
//            System.out.println("bean = " + bean);

//             System.out.println("context.getBean(\"annotatedBean\", AnnotatedBean.class) = " + context.getBean("annotatedBean", AnnotatedBean.class));
            TestJavaConfigBean javaConfigBean = context.getBean("javaConfigBean", TestJavaConfigBean.class);
            System.out.println("javaConfigBean.getBatch() = " + javaConfigBean.getBatch());
            System.out.println("javaConfigBean.getTimeout() = " + javaConfigBean.getTimeout());

            try {
                TimeUnit.SECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
