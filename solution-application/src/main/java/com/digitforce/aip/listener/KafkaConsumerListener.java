package com.digitforce.aip.listener;

import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.service.cmd.SolutionCmdService;
import com.digitforce.aip.service.qry.SolutionQueryService;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineQryFacade;
import com.digitforce.bdp.operatex.core.event.PublishEventData;
import com.digitforce.bdp.operatex.core.event.TaskInstanceStateMessage;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.util.GsonUtil;
import com.google.gson.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class KafkaConsumerListener {
    @Resource
    private SolutionCmdService solutionCmdService;
    @Resource
    private SolutionQueryService solutionQueryService;
    @Resource
    private TaskDefineQryFacade taskDefineQryFacade;
    private static final JsonSerializer<LocalDateTime> jsonSerializer =
            (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(
                    localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    //反序列化
    private static final JsonDeserializer<LocalDateTime> jsonDeserializer =
            (jsonElement, type, jsonDeserializationContext) -> LocalDateTime.parse(
                    jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    private static final Gson gson =
            new GsonBuilder()
                    .registerTypeAdapter(LocalDateTime.class, jsonSerializer)
                    .registerTypeAdapter(LocalDateTime.class, jsonDeserializer)
                    .create();

    @KafkaListener(topics = {GlobalConstant.TASK_STATUS_TOPIC})
    @Transactional(rollbackFor = RuntimeException.class)
    public void taskStateListener(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        log.info("topic:{}, offset:{}, value:{}", consumerRecord.topic(), consumerRecord.offset(),
                consumerRecord.value());
        PublishEventData publishEventData = GsonUtil.gsonToBean(consumerRecord.value().toString(),
                PublishEventData.class);
//        TaskDefineVO taskDefineVO = GsonUtil.gsonToBean(publishEventData.getData().toString(),
//                TaskDefineVO.class);
        log.info("{}", publishEventData);
    }

    @KafkaListener(topics = {GlobalConstant.TASK_INSTANCE_STATUS_TOPIC})
    @Transactional(rollbackFor = RuntimeException.class)
    public void taskInstanceStateListener(ConsumerRecord<?, ?> consumerRecord) throws IOException {
        log.info("topic:{}, offset:{}, value:{}", consumerRecord.topic(), consumerRecord.offset(),
                consumerRecord.value());
        MessageDTO message = gson.fromJson(consumerRecord.value().toString(), MessageDTO.class);
        TenantContext.init(message.getTenantId());
        TaskInstanceStateMessage instanceStateMessage = message.getData();
        switch (instanceStateMessage.getStatus()) {
            case SUBMITTED_SUCCESS:
            case RUNNING:
                solutionCmdService.onExecuting(instanceStateMessage.getTaskDefineId());
                break;
            case SUCCESS:
                solutionCmdService.onFinished(instanceStateMessage.getTaskDefineId());
                break;
            case FAILURE:
            case KILL:
                solutionCmdService.onFailed(instanceStateMessage.getTaskDefineId());
                break;
            default:
                log.warn("unknown task instance status.[status={}]", instanceStateMessage.getStatus());
        }
    }
}
