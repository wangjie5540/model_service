package com.digitforce.aip.dto.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SceneDynamicFromDTO {
    @Schema(description = "场景id")
    private Object dataSource;
    private Object trainSample;
    private Object model;
}
