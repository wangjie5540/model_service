package com.digitforce.aip.dto.cmd;

import com.digitforce.aip.dto.data.PipelineDataSource;
import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.temporal.ChronoUnit;

/**
 * 新增方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
    description = "新增方案实体类"
)
@Data
public class SolutionAddCmd extends Command {
    private Long templateId;
    @Parameter(required = true)
    private String name;
    @Parameter(required = true)
    private String scene;
    private String description;
    private String schedule;
    private Integer timeRange;
    private ChronoUnit timeUnit;
    private Object selection;
    private Boolean needExecute = false;
    private String pipelineId;
    private String pipelineName;
    private PipelineDataSource dataSource;
}
