package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * 策略命令接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:40
 */
@RestController
public class SolutionServingCmdFacadeImpl implements SolutionServingCmdFacade {
    @Override
    public Result<SolutionServingDTO> add(SolutionServingAddCmd implementAddCmd) {
        return null;
    }
}
