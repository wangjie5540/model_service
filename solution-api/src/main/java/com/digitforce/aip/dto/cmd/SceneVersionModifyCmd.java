package com.digitforce.aip.dto.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class SceneVersionModifyCmd implements Serializable {
    private static final long serialVersionUID = 2577287189426845379L;
    @Schema(description = "场景版本名称")
    private String name;
    @Schema(description = "核心算法")
    private String algorithm;
}
