package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.RunStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolutionRunDTO {
    private Long solutionId;
    private RunStatusEnum status;
    private LocalDateTime createTime;
    private Float effect;
}
