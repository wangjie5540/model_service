package com.digitforce.aip.dto.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * 新增方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(description = "新增方案实体类")
@Data
public class SolutionAddCmd {
    private static final long serialVersionUID = -4779538904025837917L;
    private String title;
    private Long sceneId;
    private String sceneName;
    private String system;
    private String description;
    private Object formInfo;
    private Map<String, Object> templateParams;
    private boolean autoML;
    private String pipelineId;
    private String pipelineName;
}
