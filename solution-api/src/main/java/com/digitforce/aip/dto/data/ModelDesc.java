package com.digitforce.aip.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class ModelDesc {
    private String modelName;
    private String type;
    private String modelHdfsPath;
    private List<ModelMetric> metrics;
}
