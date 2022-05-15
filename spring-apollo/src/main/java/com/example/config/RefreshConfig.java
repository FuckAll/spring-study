package com.example.config;

import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.context.scope.refresh.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author izgnod
 */
@Component
public class RefreshConfig {
    private static final Logger logger = LoggerFactory.getLogger(RefreshConfig.class);

    private final SampleRedisConfig sampleRedisConfig;
    private final RefreshScope refreshScope;
    private final AnnotatedBean annotatedBean;

    public RefreshConfig(SampleRedisConfig sampleRedisConfig, RefreshScope refreshScope, AnnotatedBean annotatedBean) {
        this.sampleRedisConfig = sampleRedisConfig;
        this.refreshScope = refreshScope;
        this.annotatedBean = annotatedBean;
    }

    @ApolloConfigChangeListener(value = "other", interestedKeyPrefixes = "redis")
    public void onChange(ConfigChangeEvent changeEvent) {
        logger.info("sampleRedisConfig refresh before = " + sampleRedisConfig);
        refreshScope.refresh("sampleRedisConfig");
        logger.info("sampleRedisConfig refresh after = " + sampleRedisConfig);
    }
}
