package com.example.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

/**
 /**
 * You may set up data like the following in Apollo:
 * <br /><br />
 * Properties Sample: application.properties
 * <pre>
 * redis.cache.enabled = true
 * redis.cache.expireSeconds = 100
 * redis.cache.clusterNodes = 1,2
 * redis.cache.commandTimeout = 50
 * redis.cache.someMap.key1 = a
 * redis.cache.someMap.key2 = b
 * redis.cache.someList[0] = c
 * redis.cache.someList[1] = d
 * </pre>
 *
 * Yaml Sample: application.yaml
 * <pre>
 * redis:
 *   cache:
 *     enabled: true
 *     expireSeconds: 100
 *     clusterNodes: 1,2
 *     commandTimeout: 50
 *     someMap:
 *       key1: a
 *       key2: b
 *     someList:
 *     - c
 *     - d
 * </pre>
 * 此种基于@ConfigurationProperties的配置，如果想要动态刷新
 * @author izgnod
 */
@ConditionalOnProperty("redis.cache.enabled")
@ConfigurationProperties(prefix = "redis.cache")
@Component("sampleRedisConfig")
@RefreshScope
public class SampleRedisConfig {
    private static final Logger logger = LoggerFactory.getLogger(SampleRedisConfig.class);

    private int expireSeconds;
    private String clusterNodes;
    private int commandTimeout;

    private Map<String, String> someMap = Maps.newLinkedHashMap();
    private List<String> someList = Lists.newLinkedList();

    @PostConstruct
    private void initialize() {
        logger.info(
                "SampleRedisConfig initialized - expireSeconds: {}, clusterNodes: {}, commandTimeout: {}, someMap: {}, someList: {}",
                expireSeconds, clusterNodes, commandTimeout, someMap, someList);
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public void setClusterNodes(String clusterNodes) {
        this.clusterNodes = clusterNodes;
    }

    public void setCommandTimeout(int commandTimeout) {
        this.commandTimeout = commandTimeout;
    }

    public Map<String, String> getSomeMap() {
        return someMap;
    }

    public List<String> getSomeList() {
        return someList;
    }

    @Override
    public String toString() {
        return String.format(
                "[SampleRedisConfig] expireSeconds: %d, clusterNodes: %s, commandTimeout: %d, someMap: %s, someList: %s",
                expireSeconds, clusterNodes, commandTimeout, someMap, someList);
    }
}