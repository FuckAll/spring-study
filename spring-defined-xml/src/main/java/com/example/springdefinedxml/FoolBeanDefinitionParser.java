package com.example.springdefinedxml;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;


/**
 * @author izgnod
 */
public class FoolBeanDefinitionParser implements BeanDefinitionParser {

    private final Class<?> beanClass;

    public FoolBeanDefinitionParser(Class<?> beanClass) {
        this.beanClass = beanClass;
    }


    private BeanDefinition parse(Element element, ParserContext parserContext, Class<?> beanClass) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClass(beanClass);
        beanDefinition.setLazyInit(false);
        String name = element.getAttribute("name");
        beanDefinition.getPropertyValues().addPropertyValue("name", name);
        parserContext.getRegistry().registerBeanDefinition(name, beanDefinition);
        return beanDefinition;
    }


    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return this.parse(element, parserContext, this.beanClass);
    }
}
