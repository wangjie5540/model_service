package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.qry.SolutionRunPageByQry;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.framework.api.dto.PageView;

import java.util.Map;

/**
 * <p>
 * 方案执行表 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
public interface ISolutionRunService extends IService<SolutionRun> {
    Long createRun(Solution solution, SolutionRunTypeEnum type, Map<String, Object> templateParams);

    void stopRun(Long solutionRunId);

    PageView<SolutionRun> page(SolutionRunPageByQry solutionRunPageByQry);

    SolutionRun getLatestVersion(Long solutionId);
}
