package com.digitforce.aip.listener;

import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.service.cmd.SolutionCmdService;
import com.digitforce.aip.service.qry.SolutionQueryService;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineQryFacade;
import com.digitforce.bdp.operatex.core.event.TaskInstanceStateMessage;
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
    @Resource
    private SolutionQueryService solutionQueryService;
    @Resource
    private TaskDefineQryFacade taskDefineQryFacade;

    @KafkaListener(topics = {GlobalConstant.TASK_STATUS_TOPIC})
    @Transactional(rollbackFor = RuntimeException.class)
    public void taskStateListener(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        log.info("topic:{}, offset:{}, value:{}", consumerRecord.topic(), consumerRecord.offset(),
                consumerRecord.value());
//        MessageDTO messageDTO = GsonUtil.gsonToBean((String) consumerRecord.value(), MessageDTO.class);
//        System.out.println(messageDTO);
//        // 调用任务状态变更接口
//        solutionCmdService.finish(messageDTO.getTaskId());
    }

    @KafkaListener(topics = {GlobalConstant.TASK_INSTANCE_STATUS_TOPIC})
    @Transactional(rollbackFor = RuntimeException.class)
    public void taskInstanceStateListener(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        log.info("topic:{}, offset:{}, value:{}", consumerRecord.topic(), consumerRecord.offset(),
                consumerRecord.value());
        TaskInstanceStateMessage message = GsonUtil.gsonToBean((String) consumerRecord.value(),
                TaskInstanceStateMessage.class);
        switch (message.getStatus()) {
            case SUBMITTED_SUCCESS:
                solutionCmdService.finish(message.getTaskDefineId());
                break;
            case SUCCESS:
                break;
            case FAILURE:
            case KILL:
                break;
            default:
                return;
        }
//        System.out.println(messageDTO);
//        // 调用任务状态变更接口
//        solutionCmdService.finish(messageDTO.getTaskId());
    }
}
