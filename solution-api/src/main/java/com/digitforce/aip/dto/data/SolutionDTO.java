package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.SolutionStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolutionDTO {
    @Schema(description = "主键id")
    private Long id;
    @Schema(description = "方案标题")
    private String title;
    @Schema(description = "方案描述")
    private String description;
    @Schema(description = "场景id")
    private Long sceneId;
    @Schema(description = "场景名称")
    private String sceneName;
    @Schema(description = "应用系统")
    private String system;
    @Schema(description = "时间表达式")
    private String cron;
    @Schema(description = "时间描述，用于前端反显")
    private String cronDesc;
    @Schema(description = "状态")
    private SolutionStatusEnum status;
    @Schema(description = "创建人")
    private String createUser;
    @Schema(description = "更新人")
    private LocalDateTime createTime;
}
