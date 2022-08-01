package com.digitforce.aip.facade;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.digitforce.aip.domain.SolutionServing;
import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.SolutionServingGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.aip.service.qry.SolutionServingQryService;
import com.digitforce.framework.api.dto.PageQuery;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
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
    private SolutionServingQryService solutionServingQryService;

    @Override

    public Result<SolutionServingDTO> getById(SolutionServingGetByIdQry solutionServingGetByIdQry) {
        SolutionServing solutionServing = solutionServingQryService.getById(solutionServingGetByIdQry.getId());
        return Result.success(
                solutionServing == null ? null : ConvertTool.convert(solutionServing, SolutionServingDTO.class));
    }

    @Override
    public Result<PageView<SolutionServingDTO>> pageBy(SolutionServingPageByQry solutionServingPageByQry) {
        PageQuery<SolutionServing> pageQuery = Convert.convert(new TypeReference<PageQuery<SolutionServing>>() {
        }, solutionServingPageByQry);
        PageView<SolutionServing> solutionServingPageView = solutionServingQryService.pageBy(pageQuery);
        PageView<SolutionServingDTO> solutionServingDTOPageView = PageTool.pageView(solutionServingPageView,
                SolutionServingDTO.class);
        return Result.success(solutionServingDTOPageView);
    }
}
