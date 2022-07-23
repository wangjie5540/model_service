package com.digitforce.aip.listener;

import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.service.cmd.SolutionCmdService;
import com.digitforce.aip.service.qry.SolutionQueryService;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineQryFacade;
import com.digitforce.bdp.operatex.core.event.TaskDefineStateMessage;
import com.digitforce.bdp.operatex.core.event.TaskInstanceStateMessage;
import com.digitforce.framework.util.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import javax.annotation.Resource;

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
        TaskDefineStateMessage message =
            GsonUtil.gsonToBean((String) consumerRecord.value(), TaskDefineStateMessage.class);
        switch (message.getStatus()) {
            case ONLINE:
                log.info("task is online.[taskId={},name={}]", message.getId(), message.getName());
                break;
            case OFFLINE:
                log.info("task is offline.[taskId={},name={}]", message.getId(), message.getName());
                break;
            default:
        }
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
            case RUNNING:
                solutionCmdService.onExecuting(message.getTaskDefineId());
                break;
            case SUCCESS:
                solutionCmdService.onFinished(message.getTaskDefineId());
                break;
            case FAILURE:
            case KILL:
                solutionCmdService.onFailed(message.getTaskDefineId());
                break;
            default:
                log.warn("unknown task instance status.[status={}]", message.getStatus());
        }
    }
}
