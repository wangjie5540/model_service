package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SceneStatusCmd extends Command {
    private Long id;
}
