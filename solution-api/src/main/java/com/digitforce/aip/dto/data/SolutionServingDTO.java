package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.ServingTypeEnum;
import com.digitforce.framework.domain.AggregateRoot;
import lombok.Data;

import java.util.List;

@Data
public class SolutionServingDTO extends AggregateRoot<Long> {
    private Long solutionId;
    private Long templateId;
    private List<TableSelection> selection;
    private ServingTypeEnum servingType;
}
