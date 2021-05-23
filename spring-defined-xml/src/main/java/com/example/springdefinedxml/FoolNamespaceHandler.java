package com.example.springdefinedxml;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author izgnod
 */
public class FoolNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        super.registerBeanDefinitionParser("application", new FoolBeanDefinitionParser(ApplicationConfig.class));
        super.registerBeanDefinitionParser("service", new FoolBeanDefinitionParser(ServiceConfig.class));
    }
}
