package com.digitforce.aip.dto.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 方案状态命令实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(description = "发布方案实体类")
@Data
public class SolutionPublishCmd {
    private Long id;
    private String cron;
    private Object cronDesc;
}
