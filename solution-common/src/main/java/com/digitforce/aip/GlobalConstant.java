package com.digitforce.aip;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalConstant {
    public final String DEFAULT_CRON = "0 1 * * *";
    // TODO 后续需追加项目隔离的配置
    public final Long DEFAULT_PROJECT_ID = 10L;
    public final String TASK_STATUS_TOPIC = "TASK_STATUS_TOPIC";
    public final String TASK_INSTANCE_STATUS_TOPIC = "TASK_INSTANCE_STATUS_TOPIC";
}
