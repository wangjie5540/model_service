package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.framework.operation.CrudOperation;

import java.util.List;

/**
 * 方案命令服务接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:57
 */
public interface SolutionCmdService extends CrudOperation<Solution> {
    void add(SolutionAddCmd solutionAddCmd);

    void execute(Long id);

    void batchExecute(List<Long> ids);

    void onExecuting(Long taskId, Long taskInstanceId);

    void on(Long id);

    void off(Long id);

    void onFinished(Long taskId, Long taskInstanceId);

    void stop(Long id);

    void onStopping(Long taskId, Long taskInstanceId);

    void onFailed(Long taskId, Long taskInstanceId);

    void delete(Long id);

    void batchDelete(List<Long> ids);

    void modifyById(SolutionModifyCmd solutionModifyCmd);
}
