package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.entity.Solution;

/**
 * <p>
 * 方案表 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
public interface ISolutionService extends IService<Solution> {
    void createAndRun(SolutionAddCmd solutionAddCmd);
}
