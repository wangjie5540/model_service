package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.SceneStatusEnum;
import com.digitforce.aip.enums.SceneTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 场景实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
@Data
public class SceneDTO {
    @Schema(description = "主键id")
    private Long id;
    @Schema(description = "场景名称")
    private String name;
    @Schema(description = "版本号")
    private SceneVersionDTO versionInUse;
    @Schema(description = "算法")
    private String algorithm;
    @Schema(description = "行业")
    private String business;
    @Schema(description = "场景类型")
    private SceneTypeEnum sceneType;
    @Schema(description = "方案数量")
    private Integer solutionCount;
    @Schema(description = "服务数量")
    private Integer servingCount;
    @Schema(description = "描述")
    private String description;
    @Schema(description = "状态")
    private SceneStatusEnum status;
    @Schema(description = "创建人")
    private String createUser;
    @Schema(description = "更新人")
    private String updateUser;
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
