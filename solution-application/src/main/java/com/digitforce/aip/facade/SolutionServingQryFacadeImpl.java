package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.PageTool;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private ISolutionService solutionService;

    @Override
    public Result<PageView<SolutionServingDTO>> pageBy(SolutionServingPageByQry solutionServingPageByQry) {
        PageView<SolutionServing> solutionPageView = solutionServingService.page(solutionServingPageByQry);
        List<Long> solutionIds =
            solutionPageView.getList().stream().map(SolutionServing::getSolutionId).collect(Collectors.toList());
        List<Solution> solutionList = solutionService.listByIds(solutionIds);
        PageView<SolutionServingDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
            SolutionServingDTO.class);
        for (int i = 0; i < solutionDTOPageView.getCount(); i++) {
            SolutionServingDTO solutionServingDTO = solutionDTOPageView.getList().get(i);
            Solution solution = solutionList.get(i);
            solutionServingDTO.setSceneName(solution.getSceneName());
            solutionServingDTO.setSolutionTitle(solution.getTitle());
            solutionServingDTO.setSystem(solution.getSystem());
            solutionServingDTO.setCronDesc(solution.getCronDesc());
        }
        return Result.success(solutionDTOPageView);
    }
}
