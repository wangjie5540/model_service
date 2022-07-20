package com.digitforce.aip.facade;

import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.data.PipelineParameterDTO;
import com.digitforce.aip.dto.data.SolutionTemplateDTO;
import com.digitforce.aip.dto.data.TemplateStatusListDTO;
import com.digitforce.aip.dto.qry.SolutionTemplateGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.aip.enums.TemplateStatusEnum;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
import com.digitforce.aip.model.Pipeline;
import com.digitforce.aip.service.qry.SolutionTemplateQryService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.digitforce.framework.util.GsonUtil;
import com.google.common.collect.Lists;
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
    @Resource
    private SolutionTemplateQryService solutionTemplateQryService;
    @Resource
    private KubeflowProperties kubeflowProperties;
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;

    @Override
    public Result<SolutionTemplateDTO> getById(SolutionTemplateGetByIdQry solutionGetByIdQry) {
        SolutionTemplate solutionTemplate = solutionTemplateQryService.getById(solutionGetByIdQry.getId());
        if (solutionTemplate == null) {
            return Result.success(null);
        }
        SolutionTemplateDTO solutionTemplateDTO = ConvertTool.convert(solutionTemplate, SolutionTemplateDTO.class);
        Pipeline pipeline = KubeflowHelper.getPipelineDetail(kubeflowProperties.getHost(),
                kubeflowProperties.getPort(), solutionTemplateDTO.getPipelineId());
        solutionTemplateDTO.setPipelineParameter(GsonUtil.gsonToBean(pipeline.getDescription(),
                PipelineParameterDTO.class));
        solutionTemplateMapper.browseCountInc(solutionGetByIdQry.getId());
        return Result.success(solutionTemplateDTO);
    }

    @Override
    public Result<PageView<SolutionTemplateDTO>> pageBy(@RequestBody SolutionTemplatePageByQry templatePageByQry) {
        PageView<SolutionTemplate> templatePageView = solutionTemplateQryService.pageBy(templatePageByQry);
        PageView<SolutionTemplateDTO> solutionDTOPageView = PageTool.pageView(templatePageView,
                SolutionTemplateDTO.class);
        return Result.success(solutionDTOPageView);
    }

    @Override
    public Result<TemplateStatusListDTO> statusListBy() {
        List<TemplateStatusEnum> statusList = Lists.newArrayList(TemplateStatusEnum.ONLINE, TemplateStatusEnum.OFFLINE);
        TemplateStatusListDTO templateStatusListDTO = new TemplateStatusListDTO();
        templateStatusListDTO.setStatusList(statusList);
        return Result.success(templateStatusListDTO);
    }
}
