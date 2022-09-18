package com.digitforce.aip.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * kafka参数配置类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 21:08
 */
@Data
@ConfigurationProperties(prefix = KafkaProperties.PREFIX)
public class KafkaProperties {
    public static final String PREFIX = "digitforce.kafka";
    private String taskStatusTopic;
    private String taskInstanceStatusTopic;
    private String solutionServingStatusTopic;
}
