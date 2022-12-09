package com.digitforce.aip.dto.cmd;

import com.digitforce.aip.enums.SceneTypeEnum;
import com.digitforce.framework.api.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SceneAddCmd extends Command {
    private String name;
    private String business;
    private SceneTypeEnum sceneType;
    private String targetSystem;
    private String pipelineId;
    private String pipelineName;
    private String algorithm;
    private String description;
}
