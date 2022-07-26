package com.victor2022.farlock;

import com.victor2022.farlock.config.BasicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: victor2022
 * @date: 2022/07/26  下午12:07
 * @description:
 */
@Configuration
@EnableConfigurationProperties(BasicConfig.class)
@ConditionalOnClass(FarlockFactory.class)
public class FarlockAutoConfiguration {

    @Autowired
    private BasicConfig config;

    @Bean
    public FarlockFactory getFarlockFactory(){
        return new FarlockFactory(config);
    }
}
