package com.digitforce.aip.listener;

import com.digitforce.bdp.operatex.core.event.TaskInstanceStateMessage;
import lombok.Data;

/**
 * 兼容任务服务的Message
 * TODO 后续推进任务服务优化结构
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/07/24 17:19
 */
@Data
public class MessageDTO {
    private TaskInstanceStateMessage data;
    private Integer tenantId;
}
