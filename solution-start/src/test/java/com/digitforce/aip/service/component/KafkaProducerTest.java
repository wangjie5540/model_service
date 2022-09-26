package com.digitforce.aip.service.component;

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
public class KafkaProducerTest {
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    public void sendTest() {
        kafkaTemplate.send("wtg_test", "123123");
    }
}