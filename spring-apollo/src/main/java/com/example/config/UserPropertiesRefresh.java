package com.example.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.springframework.beans.BeansException;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author izgnod
 */
@Configuration
public class UserPropertiesRefresh implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    @ApolloConfigChangeListener(value = "redis", interestedKeyPrefixes = {"redis"})
    private void refresh(ConfigChangeEvent changeEvent) {
        System.out.println("changeEvent.changedKeys() = " + changeEvent.changedKeys());
        applicationContext.publishEvent(new EnvironmentChangeEvent(changeEvent.changedKeys()));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
