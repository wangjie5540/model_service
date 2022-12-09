package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 方案状态命令实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(description = "方案状态命令实体类")
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionStatusCmd extends Command {
    private Long id;
}
