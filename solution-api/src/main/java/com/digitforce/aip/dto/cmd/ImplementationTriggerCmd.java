package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 新增方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
        description = "触发执行"
)
public class ImplementationTriggerCmd extends Command {
    private Long implementationId;

    public Long getImplementationId() {
        return implementationId;
    }

    public void setImplementationId(Long implementationId) {
        this.implementationId = implementationId;
    }
}
