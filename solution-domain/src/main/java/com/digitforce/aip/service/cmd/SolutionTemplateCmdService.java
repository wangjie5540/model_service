package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.cmd.SolutionTemplateAddCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateModifyCmd;
import com.digitforce.framework.operation.CrudOperation;

/**
 * 方案命令服务接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:57
 */
public interface SolutionTemplateCmdService extends CrudOperation<SolutionTemplate> {
    void add(SolutionTemplateAddCmd solutionAddCmd);

    void on(Long id);

    void off(Long id);

    void delete(Long id);

    void modify(SolutionTemplateModifyCmd solutionModifyCmd);
}
