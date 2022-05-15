package com.example;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Hello world!
 * 结论：可以看出，此时
 * @author izgnod
 */
public class Main {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "doc");

        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestService.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.printf("-------------执行方法开始: %s --------------%n", method.getName());
                final Object result = methodProxy.invokeSuper(o, objects);
                System.out.printf("-------------执行方法结束: %s --------------%n", method.getName());
                return result;
            }
        });
        final TestService cast = (TestService) enhancer.create();
        cast.testA();
    }
}
