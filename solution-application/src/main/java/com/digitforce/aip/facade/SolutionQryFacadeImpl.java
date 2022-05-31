package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * 方案查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SolutionQryFacadeImpl implements SolutionQryFacade {
    @Override
    public Result<SolutionDTO> getById(Long id) {
        return null;
    }
}
