package com.digitforce.aip.listener;

import com.digitforce.aip.config.KafkaProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class KafkaConsumerListenerTest {
    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;
    @Resource
    private KafkaProperties kafkaProperties;

    @Test
    public void producerTest() {
        kafkaTemplate.send(kafkaProperties.getTaskStatusTopic(), "{\"taskId\":123123}");
    }
}