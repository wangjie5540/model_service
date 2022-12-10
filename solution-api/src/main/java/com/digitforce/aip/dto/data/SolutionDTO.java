package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.SolutionStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolutionDTO {
    private Long id;
    private String title;
    private String description;
    private Long sceneId;
    private String sceneName;
    private String system;
    private String cron;
    private String cronDesc;
    private SolutionStatusEnum status;
    private LocalDateTime nextFireTime;
    private String createUser;
    private LocalDateTime createTime;
}
