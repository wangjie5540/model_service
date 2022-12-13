package com.digitforce.aip.config;

import com.digitforce.aip.GlobalConstant;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * kafka-config
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 21:11
 */
// TODO 目前不需要接入kafka，后续需要接入时，再开启
//@Configuration
//@EnableConfigurationProperties(value = KafkaProperties.class)
//@EnableKafka
public class KafkaConfig implements InitializingBean {
    @Resource
    private KafkaProperties properties;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty(GlobalConstant.TASK_STATUS_TOPIC, properties.getTaskStatusTopic());
        System.setProperty(GlobalConstant.TASK_INSTANCE_STATUS_TOPIC, properties.getTaskInstanceStatusTopic());
    }
}
