package com.digitforce.aip.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * kubeflow属性类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 21:11
 */
@Configuration
@EnableConfigurationProperties(value = KubeflowProperties.class)
public class KubeflowConfig {
}
