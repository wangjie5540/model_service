package com.digitforce.aip.facade;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.qry.SolutionGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.aip.service.qry.SolutionQueryService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 方案查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SolutionQryFacadeImpl implements SolutionQryFacade {
    @Resource
    private SolutionQueryService solutionQueryService;

    @Override
    public Result<SolutionDTO> getById(SolutionGetByIdQry solutionGetByIdQry) {
        Solution solution = solutionQueryService.getById(solutionGetByIdQry.getId());
        SolutionDTO solutionDTO = ConvertTool.convert(solution, SolutionDTO.class);
        return Result.success(solutionDTO);
    }

    @Override
    public Result<PageView<SolutionDTO>> pageBy(SolutionPageByQry solutionPageByQry) {
        PageView<Solution> solutionPageView = solutionQueryService.pageBy(solutionPageByQry);
        PageView<SolutionDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
            SolutionDTO.class);
        return Result.success(solutionDTOPageView);
    }
}
