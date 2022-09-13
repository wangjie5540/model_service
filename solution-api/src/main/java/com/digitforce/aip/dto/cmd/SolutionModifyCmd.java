package com.digitforce.aip.dto.cmd;

import com.digitforce.aip.dto.data.PipelineDataSource;
import com.digitforce.aip.dto.data.TableSelection;
import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * 编辑方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
        description = "编辑方案实体类"
)
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionModifyCmd extends Command {
    @Parameter(required = true)
    private Long id;
    private String name;
    private String description;
    private String schedule;
    private Integer timeRange;
    private ChronoUnit timeUnit;
    private List<TableSelection> selection;
    private PipelineDataSource dataSource;
    private Boolean needExecute = false;
}
