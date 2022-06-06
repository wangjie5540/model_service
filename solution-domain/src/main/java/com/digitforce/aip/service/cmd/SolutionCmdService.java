package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.framework.operation.CrudOperation;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:57
 */
public interface SolutionCmdService extends CrudOperation<Solution> {
    void add(SolutionAddCmd solutionAddCmd);

    void on(Long id);

    void off(Long id);

    void delete(Long id);

    void modify(SolutionModifyCmd solutionModifyCmd);
}
