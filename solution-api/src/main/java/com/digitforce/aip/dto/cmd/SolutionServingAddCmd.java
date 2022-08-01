package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 新增方案服务实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
        description = "新增方案服务实体类"
)
@Data
public class SolutionServingAddCmd extends Command {
    private Long solutionId;
    private Object config;
}
