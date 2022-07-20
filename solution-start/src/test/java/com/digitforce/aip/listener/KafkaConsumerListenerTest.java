package com.digitforce.aip.listener;

import com.digitforce.aip.GlobalConstant;
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

    @Test
    public void producerTest() {
        kafkaTemplate.send(GlobalConstant.TASK_STATUS_TOPIC, "{\"taskId\":123123}");
    }
}