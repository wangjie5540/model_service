package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.data.PipelineParams;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;

/**
 * <p>
 * 方案执行表 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
public interface ISolutionRunService extends IService<SolutionRun> {
    void createRun(Solution solution, PipelineParams pipelineParams, SolutionRunTypeEnum type);

    RunStatusEnum getRunStatus(String runId);
}
