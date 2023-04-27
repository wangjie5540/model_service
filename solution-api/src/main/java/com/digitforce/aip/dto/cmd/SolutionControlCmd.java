package com.digitforce.aip.dto.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 方案控制实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(description = "方案控制实体类")
@Data
@EqualsAndHashCode(callSuper = false)
public class SolutionControlCmd {
    private Long id;
}
