package com.digitforce.aip.dto.cmd;

import lombok.Data;

import java.io.Serializable;

@Data
public class SceneModifyCmd implements Serializable {
    private static final long serialVersionUID = -232458767993813648L;
    private Long id;
    private String name;
    private SceneVersionModifyCmd sceneVersion;
    private String remark;
    private String description;
}
