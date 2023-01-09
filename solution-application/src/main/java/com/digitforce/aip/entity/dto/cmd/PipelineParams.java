package com.digitforce.aip.entity.dto.cmd;

import lombok.Data;

import java.util.Map;

@Data
public class PipelineParams {
    private Map<String, Object> global_params;
    private String flag;
}
