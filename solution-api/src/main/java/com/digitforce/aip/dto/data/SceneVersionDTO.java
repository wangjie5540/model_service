package com.digitforce.aip.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 场景版本实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/09
 */
@Data
public class SceneVersionDTO {
    private Long id;
    private String name;
    private String pipelineId;
    private String pipelineName;
    private String algorithmInUse;
    private String createUser;
    private String updateUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
