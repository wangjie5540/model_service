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
@Schema(description = "发布方案实体类")
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionPublishCmd extends Command {
    private static final long serialVersionUID = -1619922236953690802L;
    private Long id;
    private String cron;
    private Object cronDesc;
}
