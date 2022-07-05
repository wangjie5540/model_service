package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.qry.SolutionGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionListBySolutionQry;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 策略查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SolutionQryFacadeImpl implements SolutionQryFacade {
    @Override
    public Result<SolutionDTO> getById(SolutionGetByIdQry implementationGetByIdQry) {
        return null;
    }

    @Override
    public Result<List<SolutionDTO>> listBySolutionId(SolutionListBySolutionQry implementationListBySolutionIdQry) {
        return null;
    }
}
