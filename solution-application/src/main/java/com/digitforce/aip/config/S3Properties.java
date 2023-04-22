package com.digitforce.aip.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = S3Properties.PREFIX)
public class S3Properties {
    public static final String PREFIX = "digitforce.s3";
    private String accessKey;
    private String secretKey;
    private String endpoint;
    private String bucket;
    private String region;
}
