package com.digitforce.aip.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceSignInterceptor implements RequestInterceptor {

    private static final Logger log = LoggerFactory.getLogger(ServiceSignInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        // 参考ServiceSignContext，类似于租户的签名
        template.header("SERVICE_SIGN", "solution");
    }
}
