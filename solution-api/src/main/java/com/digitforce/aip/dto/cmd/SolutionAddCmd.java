package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 新增方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(description = "新增方案实体类")
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionAddCmd extends Command {
    private String title;
    private Long sceneId;
    private String sceneName;
    private String system;
    private String description;
    private Object trainingSampleParams;
    private Object modelHyperParams;
    private boolean isAutoML;
    private String pipelineId;
    private String pipelineName;
}
