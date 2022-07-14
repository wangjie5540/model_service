package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.ServingTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolutionServingDTO {
    private Long id;
    private Long solutionId;
    private Long templateId;
    private String config;
    private ServingTypeEnum servingType;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
