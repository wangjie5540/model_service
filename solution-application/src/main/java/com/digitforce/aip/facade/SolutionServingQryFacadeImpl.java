package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.SolutionServingGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 方案服务查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SolutionServingQryFacadeImpl implements SolutionServingQryFacade {
    @Resource
    private ISolutionServingService solutionServingService;

    @Override
    public Result<PageView<SolutionServingDTO>> pageBy(SolutionServingPageByQry solutionServingPageByQry) {
        if (Objects.nonNull(solutionServingPageByQry.getMyOwn()) && solutionServingPageByQry.getMyOwn()) {
            SolutionServingDTO solutionServingDTO = Objects.isNull(solutionServingPageByQry.getClause()) ?
                    new SolutionServingDTO() : solutionServingPageByQry.getClause();
            solutionServingDTO.setCreateUser(TenantContext.tenant().getUserAccount());
            solutionServingPageByQry.setClause(solutionServingDTO);
        }
        PageView<SolutionServing> solutionServingPageView = solutionServingService.page(solutionServingPageByQry);
        PageView<SolutionServingDTO> solutionServingDTOPageView = PageTool.pageView(solutionServingPageView,
                SolutionServingDTO.class);
        return Result.success(solutionServingDTOPageView);
    }

    @Override
    public Result<SolutionServingDTO> getById(SolutionServingGetByIdQry solutionServingGetByIdQry) {
        SolutionServing solutionServing = solutionServingService.getById(solutionServingGetByIdQry.getId());
        return Result.success(ConvertTool.convert(solutionServing, SolutionServingDTO.class));
    }
}
