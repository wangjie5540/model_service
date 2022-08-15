package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 新增方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
        description = "新增方案实施实体类"
)
public class SolutionClearCronCmd extends Command {
    private String solutionId;

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }
}
