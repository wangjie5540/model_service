package com.digitforce.aip.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class TriggerRunCmd {
    private String name;
    private String experimentId;
    private String pipelineId;
    private List<Map<String, Object>> pipelineParameters;
}
