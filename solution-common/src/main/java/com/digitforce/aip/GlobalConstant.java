package com.digitforce.aip;

import lombok.experimental.UtilityClass;

@UtilityClass
public class GlobalConstant {
    public final String DEFAULT_CRON = "0 1 * * *";
    public final String DEFAULT_EXPERIMENT_ID = "f56c9dfe-d57d-444a-bc61-01ff9fe03a5d";
    // TODO 后续需追加项目隔离的配置
    public final Long DEFAULT_PROJECT_ID = 10L;
    public final String TASK_STATUS_TOPIC = "operatex_publish_task_define_status_change";
    public final String TASK_INSTANCE_STATUS_TOPIC = "operatex_publish_task_instance_status_change";
}
