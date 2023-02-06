package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.SceneTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolutionServingDTO {
    @Schema(description = "方案服务id")
    private Long id;
    @Schema(description = "标题")
    private String title;
    @Schema(description = "场景类型")
    private SceneTypeEnum sceneType;
    @Schema(description = "场景名称")
    private String sceneName;
    @Schema(description = "方案标题")
    private String solutionTitle;
    @Schema(description = "系统")
    private String system;
    @Schema(description = "创建用户")
    private String createUser;
    @Schema(description = "方案id")
    private Long solutionId;
    @Schema(description = "场景id")
    private Long sceneId;
    @Schema(description = "表单信息")
    private Object formInfo;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
