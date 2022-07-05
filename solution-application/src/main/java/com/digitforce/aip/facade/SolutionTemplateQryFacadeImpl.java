package com.digitforce.aip.facade;

import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.data.PipelineDTO;
import com.digitforce.aip.dto.data.PipelineParameterDTO;
import com.digitforce.aip.dto.data.SolutionTemplateDTO;
import com.digitforce.aip.dto.qry.SolutionTemplateGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.aip.service.qry.SolutionTemplateQryService;
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
public class SolutionTemplateQryFacadeImpl implements SolutionTemplateQryFacade {
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    @Resource
    private SolutionTemplateQryService solutionQryService;
    @Resource
    private KubeflowProperties kubeflowProperties;

    @Override
    public Result<SolutionTemplateDTO> getById(SolutionTemplateGetByIdQry solutionGetByIdQry) {
        SolutionTemplate solution = solutionQryService.getById(solutionGetByIdQry.getId());
        SolutionTemplateDTO solutionTemplateDTO = ConvertTool.convert(solution, SolutionTemplateDTO.class);
        PipelineDTO pipelineDetail =
                KubeflowHelper.getPipelineDetail(kubeflowProperties.getHost(), kubeflowProperties.getPort(),
                        solutionTemplateDTO.getPipelineId());

        solutionTemplateDTO.setPipelineParameter(GsonUtil.gsonToBean(pipelineDetail.getDescription(), PipelineParameterDTO.class));
        return Result.success(solutionTemplateDTO);
    }

    @Override
    public Result<List<SolutionTemplateDTO>> listBy(@RequestBody SolutionTemplateGetByIdQry solutionGetByIdQry) {
        SolutionTemplate solution = ConvertTool.convert(solutionGetByIdQry, SolutionTemplate.class);
        List<SolutionTemplate> solutionList = solutionQryService.listBy(solution);
        List<SolutionTemplateDTO> solutions = ConvertTool.convert(solutionList, SolutionTemplateDTO.class);
        return Result.success(solutions);
    }

    @Override
    public Result<PageView<SolutionTemplateDTO>> pageBy(@RequestBody PageQuery<SolutionTemplatePageByQry> solutionPageByQry) {
        PageQuery<SolutionTemplate> pageQuery = solutionPageByQry.build(d -> ConvertTool.convert(d, SolutionTemplate.class));
        PageView<SolutionTemplate> solutionPageView = solutionQryService.pageBy(pageQuery);
        PageView<SolutionTemplateDTO> solutionDTOPageView = PageTool.pageView(solutionPageView, SolutionTemplateDTO.class);
        return Result.success(solutionDTOPageView);
    }
}
