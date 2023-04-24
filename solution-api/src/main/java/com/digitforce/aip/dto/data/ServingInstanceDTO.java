package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.ServingInstanceStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServingInstanceDTO {
    private Long id;
    private Long servingId;
    private Long solutionId;
    private Long sceneId;
    private Long modelVersion;
    private ServingInstanceStatusEnum status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String result;
    private String createUser;
    private String updateUser;
}
