package com.digitforce.aip.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.mapper.SolutionRunMapper;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.KubeflowPipelineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRun(Long solutionId, String pipelineId, String pipelineName,
                          SolutionRunTypeEnum type) {
        String runName = StrUtil.format("{}-{}", pipelineName, IdUtil.getSnowflake().nextId());
        String runId = kubeflowPipelineService.createRun(pipelineId, runName);
        SolutionRun solutionRun = new SolutionRun();
        solutionRun.setSolutionId(solutionId);
        solutionRun.setPRunId(runId);
        solutionRun.setPRunName(runName);
        solutionRun.setPipelineId(pipelineId);
        solutionRun.setPipelineName(pipelineName);
        solutionRun.setType(type);
        super.save(solutionRun);
    }

    @Override
    public RunStatusEnum getRunStatus(String runId) {
        return kubeflowPipelineService.getStatus(runId);
    }
}
