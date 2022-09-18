package com.digitforce.aip.domain;

import com.digitforce.aip.enums.StatusChangeEnum;
import lombok.Data;

@Data
public class SolutionServingStatusDTO {
    private StatusChangeEnum status;
    private SolutionServing solutionServing;
}
