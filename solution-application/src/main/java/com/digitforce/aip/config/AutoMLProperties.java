package com.digitforce.aip.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * kubeflow参数配置类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 21:08
 */
@Data
@ConfigurationProperties(prefix = AutoMLProperties.PREFIX)
public class AutoMLProperties {
    public static final String PREFIX = "digitforce.automl";

    private String baseUrl;
}
