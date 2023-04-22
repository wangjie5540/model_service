package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.RunStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolutionRunDTO {
    private Long id;
    private Long version;
    private Long solutionId;
    private Long packageId;
    private RunStatusEnum status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long trainTime;
    private Float auc;
    private Float accuracy;
    private Float precision;
    private Float recall;
    private Float f1;
    private Roc roc;
}
