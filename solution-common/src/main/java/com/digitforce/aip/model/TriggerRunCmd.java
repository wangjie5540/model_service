package com.digitforce.aip.model;

import lombok.Data;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Data
public class TriggerRunCmd {
    private String name;
    private String experimentId;
    private String pipelineId;
    private Long solutionId;
    private Long instanceId;
    private Integer timeRange;
    private ChronoUnit timeUnit;
    @Deprecated
    private List<Map<String, Object>> pipelineParameters;
}
