package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionRunDTO;
import com.digitforce.aip.dto.qry.SolutionRunPageByQry;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.PageTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SolutionRunQryFacadeImpl implements SolutionRunQryFacade {
    @Resource
    private ISolutionRunService solutionRunService;

    @Override
    public Result<PageView<SolutionRunDTO>> pageBy(SolutionRunPageByQry solutionRunPageByQry) {
        PageView<SolutionRun> solutionPageView = solutionRunService.page(solutionRunPageByQry);
        PageView<SolutionRunDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
                SolutionRunDTO.class);
        return Result.success(solutionDTOPageView);
    }
}
