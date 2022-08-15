package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.cmd.SolutionTemplateAddCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateModifyCmd;
import com.digitforce.framework.operation.CrudOperation;

import java.util.List;

/**
 * 方案模板命令接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:57
 */
public interface SolutionTemplateCmdService extends CrudOperation<SolutionTemplate> {
    void add(SolutionTemplateAddCmd solutionAddCmd);

    void on(Long id);

    void batchOn(List<Long> ids);

    void off(Long id);

    void batchOff(List<Long> ids);

    void delete(Long id);

    void batchDelete(List<Long> ids);

    void modify(SolutionTemplateModifyCmd solutionModifyCmd);

    void batchModify(List<SolutionTemplate> solutionTemplateList);

    void browseCountInc(Long id);

    void applyCountInc(Long id);
}
