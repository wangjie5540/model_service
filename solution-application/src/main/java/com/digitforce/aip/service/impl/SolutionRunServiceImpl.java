package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.qry.SolutionRunPageByQry;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.PipelineRunFlagEnum;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.mapper.SolutionRunMapper;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.aip.service.component.TemplateComponent;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import javax.annotation.Resource;

/**
 * <p>
 * 方案执行表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
@Service
public class SolutionRunServiceImpl extends ServiceImpl<SolutionRunMapper, SolutionRun> implements ISolutionRunService {
    @Resource
    private KubeflowPipelineService kubeflowPipelineService;
    @Resource
    private TemplateComponent templateComponent;
    @Resource
    private SolutionRunMapper solutionRunMapper;
    @Resource
    private ISolutionService solutionService;

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public Long createRun(Solution solution, SolutionRunTypeEnum type, Map<String, Object> templateParams) {
        SolutionRun solutionRun = ConvertTool.convert(solution, SolutionRun.class);
        solutionRun.setSolutionId(solution.getId());
        solutionRun.setId(null);
        solutionRun.setCreateTime(null);
        solutionRun.setUpdateTime(null);
        solutionRun.setType(type);
        SolutionRun latestVersion = getLatestVersion(solution.getId());
        solutionRun.setVersion(latestVersion == null ? 1L : latestVersion.getVersion() + 1);
        super.save(solutionRun);
        Long solutionRunId = solutionRun.getId();
        templateParams.put("solution_run_id", solutionRunId.toString());
        String pipelineParams = templateComponent.getPipelineParams(solution.getTrainTemplate(), templateParams);
        String pRunName = StrUtil.format("{}-{}", solution.getPipelineName(), solutionRunId);
        String pRunId = kubeflowPipelineService.createRun(solution.getPipelineId(), pRunName, pipelineParams,
                PipelineRunFlagEnum.TRAIN.name());
        solutionRun = new SolutionRun();
        solutionRun.setId(solutionRunId);
        solutionRun.setPRunId(pRunId);
        solutionRun.setPRunName(pRunName);
        solutionRun.setPipelineParams(pipelineParams);
        super.updateById(solutionRun);
        return solutionRunId;
    }

    @Override
    public void startRun(Long solutionRunId) {
        SolutionRun solutionRun = super.getById(solutionRunId);
        Solution solution = solutionService.getById(solutionRun.getSolutionId());
        Map<String, Object> templateParams = solution.getTemplateParams();
        templateParams.put("solution_run_id", solutionRunId.toString());
        String pipelineParams = templateComponent.getPipelineParams(solution.getTrainTemplate(), templateParams);
        String pRunName = StrUtil.format("{}-{}", solution.getPipelineName(), solutionRunId);
        String pRunId = kubeflowPipelineService.createRun(solution.getPipelineId(), pRunName, pipelineParams,
                PipelineRunFlagEnum.TRAIN.name());
        solutionRun = new SolutionRun();
        solutionRun.setId(solutionRunId);
        solutionRun.setPRunId(pRunId);
        solutionRun.setPipelineParams(pipelineParams);
        super.updateById(solutionRun);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stopRun(Long solutionRunId) {
        SolutionRun solutionRun = super.getById(solutionRunId);
        if (solutionRun == null) {
            return;
        }
        kubeflowPipelineService.stopRun(solutionRun.getPRunId());
        solutionRun = new SolutionRun();
        solutionRun.setStatus(RunStatusEnum.Stopped);
        super.updateById(solutionRun);
    }

    @Override
    public PageView<SolutionRun> page(SolutionRunPageByQry solutionRunPageByQry) {
        QueryWrapper<SolutionRun> queryWrapper =
                new QueryWrapper<>(BeanUtil.toBean(solutionRunPageByQry.getClause(), SolutionRun.class));
        Page<SolutionRun> page = PageUtil.page(solutionRunPageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }

    @Override
    public SolutionRun getLatestVersion(Long solutionId) {
        return solutionRunMapper.getLatestRunBySolutionId(solutionId);
    }
}
