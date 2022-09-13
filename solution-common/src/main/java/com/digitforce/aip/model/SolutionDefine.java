package com.digitforce.aip.model;

import lombok.Data;

@Data
public class SolutionDefine {
    private Long id;
    private Long templateId;
    private Long taskId;
    private Long taskInstanceId;
    private String scene;
    private String name;
}
