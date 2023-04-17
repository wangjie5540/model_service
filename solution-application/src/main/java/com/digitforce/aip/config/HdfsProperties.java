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
@ConfigurationProperties(prefix = HdfsProperties.PREFIX)
public class HdfsProperties {
    public static final String PREFIX = "digitforce.hdfs";

    private String uri;
    private String configFile;
    private String modelBasePath;
    private String predictBasePath = "/user/ai/aip/predict";
}
