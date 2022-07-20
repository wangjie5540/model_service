package com.digitforce.aip.listener;

import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.service.cmd.SolutionCmdService;
import com.digitforce.framework.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;

@Component
@Slf4j
public class KafkaConsumerListener {
    @Resource
    private SolutionCmdService solutionCmdService;

    @KafkaListener(topics = {GlobalConstant.TASK_STATUS_TOPIC})
    @Transactional(rollbackFor = RuntimeException.class)
    public void taskInstanceStateListener(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        log.info("topic:{}, offset:{}, value:{}", consumerRecord.topic(), consumerRecord.offset(),
                consumerRecord.value());
        MessageDTO messageDTO = GsonUtil.gsonToBean((String) consumerRecord.value(), MessageDTO.class);
        System.out.println(messageDTO);
        // 调用任务状态变更接口
        solutionCmdService.finish(messageDTO.getTaskId());
    }
}
