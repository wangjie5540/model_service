package com.digitforce.aip.dto.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolutionServingDTO {
    private Long id;
    private String title;
    private String sceneName;
    private String solutionTitle;
    private String system;
    private String updateUser;
    private Long solutionId;
    private Long sceneId;
    private LocalDateTime createTime;
}
