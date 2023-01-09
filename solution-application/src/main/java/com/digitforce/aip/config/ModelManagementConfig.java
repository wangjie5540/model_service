package com.digitforce.aip.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 模型管理属性
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 21:11
 */
@Configuration
@EnableConfigurationProperties(value = ModelManagementProperties.class)
public class ModelManagementConfig {
}
