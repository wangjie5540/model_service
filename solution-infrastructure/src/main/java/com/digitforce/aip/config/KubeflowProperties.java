package com.digitforce.aip.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 21:08
 */
@Data
@ConfigurationProperties(prefix = KubeflowProperties.PREFIX)
public class KubeflowProperties {
    public static final String PREFIX = "digitforce.kubeflow";

    private String host;
    private Integer port;
}
