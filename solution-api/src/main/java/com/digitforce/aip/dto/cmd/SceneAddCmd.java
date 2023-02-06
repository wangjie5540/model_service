package com.digitforce.aip.dto.cmd;

import com.digitforce.aip.enums.SceneTypeEnum;
import lombok.Data;

@Data
public class SceneAddCmd {
    private String name;
    private String business;
    private SceneTypeEnum sceneType;
    private String targetSystem;
    private String pipelineId;
    private String pipelineName;
    private String algorithm;
    private String remark;
    private String description;
}
