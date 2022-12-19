package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
    void createRun(Long solutionId, String pipelineId, String pipelineName, SolutionRunTypeEnum type);

    RunStatusEnum getRunStatus(String runId);
}
