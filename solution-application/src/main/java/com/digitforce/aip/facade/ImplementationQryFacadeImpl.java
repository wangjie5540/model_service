package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.ImplementationDTO;
import com.digitforce.aip.dto.qry.ImplementationGetByIdQry;
import com.digitforce.aip.dto.qry.ImplementationListBySolutionQry;
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
public class ImplementationQryFacadeImpl implements ImplementationQryFacade {
    @Override
    public Result<ImplementationDTO> getById(ImplementationGetByIdQry implementationGetByIdQry) {
        return null;
    }

    @Override
    public Result<List<ImplementationDTO>> listBySolutionId(ImplementationListBySolutionQry implementationListBySolutionIdQry) {
        return null;
    }
}
