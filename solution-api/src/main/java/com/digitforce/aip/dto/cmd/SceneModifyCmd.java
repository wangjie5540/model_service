package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SceneModifyCmd extends Command {
    private Long id;
    private String name;
    private String algorithm;
    private String remark;
    private String description;
}
