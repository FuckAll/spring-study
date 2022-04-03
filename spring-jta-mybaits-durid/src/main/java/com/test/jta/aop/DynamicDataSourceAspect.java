package com.test.jta.aop;

import com.test.jta.config.DataSourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author wuxiangdong
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private static Logger logger = LoggerFactory.getLogger(DataSourceContextHolder.class);


    @Pointcut("@annotation(com.test.jta.aop.DataSource)")
    public void point() {
    }

    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        DataSource annotation = method.getAnnotation(DataSource.class);
        if (annotation == null) {
            DataSourceContextHolder.setDataSourceKey(DataSourceNames.DataSourceOne);
            logger.info("database switch one");
        } else {
            DataSourceContextHolder.setDataSourceKey(annotation.value());
            logger.info("database switch {}", annotation.value());
        }
        return joinPoint.proceed();
    }

    @After("point()")
    public void afterSwitch(JoinPoint joinPoint) {
        DataSourceContextHolder.clearDataSourceKey();
    }

}
