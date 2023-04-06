package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.ServingInstanceDTO;
import com.digitforce.aip.dto.qry.ServingInstanceGetByIdQry;
import com.digitforce.aip.dto.qry.ServingInstancePageByQry;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 服务实例查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class ServingInstanceQryFacadeImpl implements ServingInstanceQryFacade {
    @Resource
    private IServingInstanceService servingInstanceService;

    @Override
    public Result<PageView<ServingInstanceDTO>> pageBy(ServingInstancePageByQry servingInstancePageByQry) {
        PageView<ServingInstance> solutionPageView = servingInstanceService.page(servingInstancePageByQry);
        PageView<ServingInstanceDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
                ServingInstanceDTO.class);
        return Result.success(solutionDTOPageView);
    }

    @Override
    public Result<ServingInstanceDTO> getById(ServingInstanceGetByIdQry servingInstanceGetByIdQry) {
        ServingInstance servingInstance = servingInstanceService.getById(servingInstanceGetByIdQry.getId());
        return Result.success(ConvertTool.convert(servingInstance, ServingInstanceDTO.class));
    }
}
