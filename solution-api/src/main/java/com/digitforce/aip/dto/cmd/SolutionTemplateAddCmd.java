package com.digitforce.aip.dto.cmd;

import com.digitforce.aip.enums.TemplateStatusEnum;
import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 新增方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
        description = "新增方案模板入参类"
)
@Data
public class SolutionTemplateAddCmd extends Command {
    private String name;
    private String scene;
    private String pipelineId;
    private String pipelineName;
    private String description;
    private TemplateStatusEnum status;
}
