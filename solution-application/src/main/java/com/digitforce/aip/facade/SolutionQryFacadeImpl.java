package com.digitforce.aip.facade;

import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.data.PipelineDTO;
import com.digitforce.aip.dto.data.PipelineParameterDTO;
import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.qry.SolutionGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.aip.service.qry.SolutionQryService;
import com.digitforce.framework.api.dto.PageQuery;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.digitforce.framework.util.GsonUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 方案查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SolutionQryFacadeImpl implements SolutionQryFacade {
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    @Resource
    private SolutionQryService solutionQryService;
    @Resource
    private KubeflowProperties kubeflowProperties;

    @Override
    public Result<SolutionDTO> getById(SolutionGetByIdQry solutionGetByIdQry) {
        Solution solution = solutionQryService.getById(solutionGetByIdQry.getId());
        SolutionDTO solutionDTO = ConvertTool.convert(solution, SolutionDTO.class);
        PipelineDTO pipelineDetail =
                KubeflowHelper.getPipelineDetail(kubeflowProperties.getHost(), kubeflowProperties.getPort(),
                        solutionDTO.getPipelineId());

        solutionDTO.setPipelineParameter(GsonUtil.gsonToBean(pipelineDetail.getDescription(), PipelineParameterDTO.class));
        return Result.success(solutionDTO);
    }

    @Override
    public Result<List<SolutionDTO>> listBy(@RequestBody SolutionGetByIdQry solutionGetByIdQry) {
        Solution solution = ConvertTool.convert(solutionGetByIdQry, Solution.class);
        List<Solution> solutionList = solutionQryService.listBy(solution);
        List<SolutionDTO> solutions = ConvertTool.convert(solutionList, SolutionDTO.class);
        return Result.success(solutions);
    }

    @Override
    public Result<PageView<SolutionDTO>> pageBy(@RequestBody PageQuery<SolutionPageByQry> solutionPageByQry) {
        PageQuery<Solution> pageQuery = solutionPageByQry.build(d -> ConvertTool.convert(d, Solution.class));
        PageView<Solution> solutionPageView = solutionQryService.pageBy(pageQuery);
        PageView<SolutionDTO> solutionDTOPageView = PageTool.pageView(solutionPageView, SolutionDTO.class);
        return Result.success(solutionDTOPageView);
    }
}
