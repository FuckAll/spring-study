package com.test.jta.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wuxiangdong
 */
public class DataSourceContextHolder extends AbstractRoutingDataSource {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * @param dbName 设置数据源名
     */
    public static void setDataSourceKey(String dbName) {
        contextHolder.set(dbName);
    }

    /**
     * @return 获取数据源名
     */
    public static String getDataSourceKey() {
        return contextHolder.get();
    }

    /**
     * 清除数据源名
     */
    public static void clearDataSourceKey() {
        contextHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSourceKey();
    }
}
