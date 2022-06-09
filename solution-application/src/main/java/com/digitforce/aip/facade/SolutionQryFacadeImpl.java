package com.digitforce.aip.facade;

import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.data.PipelineDTO;
import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.qry.SolutionGetByIdQry;
import com.digitforce.aip.service.qry.SolutionQryService;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

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

        solutionDTO.setPipelineParameters(pipelineDetail.getParameters());
        solutionDTO.setPropertiesNeeded(gson.fromJson(pipelineDetail.getDescription(), HashMap.class));
        return Result.success(solutionDTO);
    }
}
