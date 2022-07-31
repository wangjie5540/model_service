package com.digitforce.aip.facade;

import com.digitforce.aip.domain.SolutionServing;
import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.SolutionServingGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.aip.service.qry.SolutionServingQryService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 策略查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SolutionServingQryFacadeImpl implements SolutionServingQryFacade {
    @Resource
    private SolutionServingQryService solutionServingQryService;

    @Override

    public Result<SolutionServingDTO> getById(SolutionServingGetByIdQry solutionServingGetByIdQry) {
        SolutionServing solutionServing = solutionServingQryService.getById(solutionServingGetByIdQry.getId());
        return Result.success(
            solutionServing == null ? null : ConvertTool.convert(solutionServing, SolutionServingDTO.class));
    }

    @Override
    public Result<PageView<SolutionServingDTO>> pageBy(SolutionServingPageByQry solutionServingPageByQry) {
        // TODO 实现分页
        return null;
    }
}
