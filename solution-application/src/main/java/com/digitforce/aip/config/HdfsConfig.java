package com.digitforce.aip.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(value = HdfsProperties.class)
public class HdfsConfig {
}
