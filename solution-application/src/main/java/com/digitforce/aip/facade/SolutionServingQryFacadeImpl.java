package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.SolutionServingGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * 方案服务查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SolutionServingQryFacadeImpl implements SolutionServingQryFacade {
//    @Resource
//    private SolutionServingQryService solutionServingQryService;

    @Override
    public Result<SolutionServingDTO> getById(SolutionServingGetByIdQry solutionServingGetByIdQry) {
//        SolutionServing solutionServing = solutionServingQryService.getById(solutionServingGetByIdQry.getId());
//        return Result.success(
//            solutionServing == null ? null : ConvertTool.convert(solutionServing, SolutionServingDTO.class));
        return null;
    }

    @Override
    public Result<PageView<SolutionServingDTO>> pageBy(SolutionServingPageByQry solutionServingPageByQry) {
//        PageQuery<SolutionServing> pageQuery = Convert.convert(new TypeReference<PageQuery<SolutionServing>>() {
//        }, solutionServingPageByQry);
//        PageView<SolutionServing> solutionServingPageView = solutionServingQryService.pageBy(pageQuery);
//        PageView<SolutionServingDTO> solutionServingDTOPageView = PageTool.pageView(solutionServingPageView,
//            SolutionServingDTO.class);
//        return Result.success(solutionServingDTOPageView);
        return null;
    }
}
