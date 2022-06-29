package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.Implementation;
import com.digitforce.aip.dto.cmd.ImplementationAddCmd;
import com.digitforce.aip.dto.cmd.ImplementationTriggerCmd;
import com.digitforce.framework.operation.CrudOperation;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:57
 */
public interface ImplementationCmdService extends CrudOperation<Implementation> {
    void add(ImplementationAddCmd implementationAddCmd);

    void triggerRun(ImplementationTriggerCmd implementationTriggerCmd);
}
