package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.PageTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
        PageView<SolutionServing> solutionServingPageView = solutionServingService.page(solutionServingPageByQry);
        PageView<SolutionServingDTO> solutionDTOPageView = PageTool.pageView(solutionServingPageView,
                SolutionServingDTO.class);
        return Result.success(solutionDTOPageView);
    }
}
