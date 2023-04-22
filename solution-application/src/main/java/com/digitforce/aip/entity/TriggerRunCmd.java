package com.digitforce.aip.entity;

import lombok.Data;

import java.time.temporal.ChronoUnit;

@Data
public class TriggerRunCmd {
    private String name;
    private String experimentId;
    private String pipelineId;
    private Long solutionId;
    private Long instanceId;
    private Integer timeRange;
    private ChronoUnit timeUnit;
}
