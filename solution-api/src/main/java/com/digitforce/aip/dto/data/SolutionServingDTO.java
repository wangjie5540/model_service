package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.ServingTypeEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class SolutionServingDTO {
    private Long id;
    private Long solutionId;
    private Long templateId;
    private List<TableSelection> selection;
    private ServingTypeEnum servingType;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
