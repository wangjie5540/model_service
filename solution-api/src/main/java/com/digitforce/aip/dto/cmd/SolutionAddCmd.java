package com.digitforce.aip.dto.cmd;

import com.digitforce.aip.dto.data.SolutionDTO;
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
    description = "新增方案实体类"
)
public class SolutionAddCmd extends Command {
    private SolutionDTO solutionDTO;

    public SolutionDTO getSolutionDTO() {
        return solutionDTO;
    }

    public void setSolutionDTO(SolutionDTO solutionDTO) {
        this.solutionDTO = solutionDTO;
    }
}
