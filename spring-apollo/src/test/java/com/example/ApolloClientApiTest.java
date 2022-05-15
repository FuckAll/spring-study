package com.example;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.ConfigFile;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.model.ConfigChange;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import org.junit.Test;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ApolloClientApiTest {

    @Test
    public void testApi(){
        Config config = ConfigService.getAppConfig(); //config instance is singleton for each namespace and is never null
        String someKey = "timeout";
        String someDefaultValue = "10011";
        String value = config.getProperty(someKey, someDefaultValue);
        Set<String> propertyNames = config.getPropertyNames();
        System.out.println("value = " + value);
        System.out.println("propertyNames = " + propertyNames);
    }

    @Test
    public void testListener() throws InterruptedException {
        Config appConfig = ConfigService.getAppConfig();
        appConfig.addChangeListener(new ConfigChangeListener() {
            @Override
            public void onChange(ConfigChangeEvent configChangeEvent) {
                Set<String> keys = configChangeEvent.changedKeys();
                for (String key : keys) {
                    ConfigChange change = configChangeEvent.getChange(key);
                    System.out.println(String.format("Found change - key: %s, oldValue: %s, newValue: %s, changeType: %s", change.getPropertyName(), change.getOldValue(), change.getNewValue(), change.getChangeType()));
                }
            }
        });

        TimeUnit.SECONDS.sleep(1000);
    }


    @Test
    public void testNameSpace(){
        Config config = ConfigService.getConfig("other");
        String someKey = "time";
        String someDefaultValue = "10011";
        String value = config.getProperty(someKey, someDefaultValue);
        Set<String> propertyNames = config.getPropertyNames();
        System.out.println("value = " + value);
        System.out.println("propertyNames = " + propertyNames);
    }

    @Test
    public void testFileXML(){
        Config config = ConfigService.getConfig("testxml.xml");
        System.out.println("config.getPropertyNames() = " + config.getPropertyNames());
        System.out.println("config.getProperty(\"content\", \"\") = " + config.getProperty("content", ""));
    }


    @Test
    public void testFileYml(){
        Config config = ConfigService.getConfig("testyml.yml");
        System.out.println("config.getPropertyNames() = " + config.getPropertyNames());
//        System.out.println("config.getProperty(\"languages\", \"\") = " + config.getProperty("languages[0]", ""));
        System.out.println("config.getArrayProperty(\"languages\", \"[]\", \"\") = " + config.getArrayProperty("languages", "[]", null));
    }
}
