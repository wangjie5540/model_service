package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.aip.dto.cmd.SolutionUnPublishCmd;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.aip.entity.Solution;
import com.digitforce.framework.api.dto.PageView;

/**
 * <p>
 * 方案表 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
public interface ISolutionService extends IService<Solution> {
    Solution add(SolutionAddCmd solutionAddCmd);

    void publish(SolutionPublishCmd solutionPublishCmd);

    void unPublish(SolutionUnPublishCmd solutionUnPublishCmd);

    PageView<Solution> page(SolutionPageByQry solutionPageByQry);

    void stop(Long solutionId);

    void delete(Long solutionId);
}
