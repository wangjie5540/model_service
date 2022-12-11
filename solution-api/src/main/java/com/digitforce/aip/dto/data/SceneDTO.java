package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.SceneStatusEnum;
import com.digitforce.aip.enums.SceneTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 场景实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
@Data
public class SceneDTO {
    @Deprecated
    private List<String> scenes;
    private Long id;
    private String name;
    private SceneVersionDTO versionInUse;
    private SceneTypeEnum sceneType;
    private Integer solutionCount;
    private Integer servingCount;
    private String description;
    private SceneStatusEnum status;
    private String createUser;
    private String updateUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
