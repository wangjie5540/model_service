package com.digitforce.aip.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 场景版本实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/09
 */
@Data
public class SceneVersionDTO {
    @Schema(description = "场景版本id")
    private Long id;
    @Schema(description = "场景版本名称")
    private String name;
    @Schema(description = "kubeflow pipeline id")
    private String pipelineId;
    @Schema(description = "kubeflow pipeline name")
    private String pipelineName;
    @Schema(description = "核心算法")
    private String algorithm;
    @Schema(description = "创建人")
    private String createUser;
    @Schema(description = "更新人")
    private String updateUser;
}
