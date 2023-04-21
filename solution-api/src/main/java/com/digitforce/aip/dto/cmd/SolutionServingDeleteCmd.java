package com.digitforce.aip.dto.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 删除方案服务实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(description = "删除方案服务实体类")
@Data
public class SolutionServingDeleteCmd {
    private Long servingId;
}
