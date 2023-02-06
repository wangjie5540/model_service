package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.ResourceTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class ModelDTO {
    private String name;
    private ResourceTypeEnum resourceType;
    private Float size;
    private List<ModelMetric> metricsList;
    private Long solutionId;
    private Long packageId;
}
