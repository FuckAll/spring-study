package com.example.config;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author izgnod
 */
public class TestJavaConfigBean {
    @Value("${timeout:100}")
    private int timeout;
    @Value("${batch:1000}")
    private int batch;

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getTimeout() {
        return timeout;
    }

    public int getBatch() {
        return batch;
    }
}
