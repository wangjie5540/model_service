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
//        String serviceSign = ServiceSignContext.serviceSign();
//        if (Objects.nonNull(serviceSign)) {
//            Method method = template.methodMetadata().method();
//            template.header("SERVICE_SIGN", serviceSign);
//            log.info("【Feign调用】- 设置head服务标识信息, url={}, serviceSign={},class={},method={}", new Object[]{template
//            .url(), serviceSign, method.getClass().getName(), method.getName()});
//        } else {
//            log.error("【Feign调用】- 未获取ServiceSignContext服务标识信息,无法传递");
//        }
        template.header("SERVICE_SIGN", "solution");
        // TODO 测试用，后续删除
        template.header("X_TENANT",
                "eyJkZXBsb3lNb2RlIjoiU0hBUkUiLCJuYW1lc3BhY2UiOiJERUZBVUxUIiwidGVuYW50SWQiOjEwMDAwLCJ1c2VyQWNjb3VudCI6InN1cGVyYWRtaW4ifQ==");
    }
}
