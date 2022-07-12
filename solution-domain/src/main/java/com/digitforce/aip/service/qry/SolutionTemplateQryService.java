package com.digitforce.aip.service.qry;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.operation.CrudOperation;

/**
 * 方案查询服务接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:33
 */
public interface SolutionTemplateQryService extends CrudOperation<SolutionTemplate> {
    PageView<SolutionTemplate> pageBy(SolutionTemplatePageByQry solutionTemplatePageByQry);
}
