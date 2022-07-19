package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.framework.operation.CrudOperation;

/**
 * 方案命令服务接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:57
 */
public interface SolutionCmdService extends CrudOperation<Solution> {
    void add(SolutionAddCmd implementationAddCmd);

    void on(Long id);

    void off(Long id);

    void execute(Long id);

    void stop(Long id);
}
