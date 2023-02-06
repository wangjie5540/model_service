package com.digitforce.aip.entity.dto.cmd;

import lombok.Data;

@Data
public class CreateAutoMlTaskCmd {
    private AutomlParams automlParams;
    private String pipelineId;
    private PipelineParams pipelineParams;
}
