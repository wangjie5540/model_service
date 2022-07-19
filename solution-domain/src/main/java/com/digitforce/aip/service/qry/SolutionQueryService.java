package com.digitforce.aip.service.qry;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.operation.CrudOperation;

/**
 * 方案查询服务接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:57
 */
public interface SolutionQueryService extends CrudOperation<Solution> {
    PageView<Solution> pageBy(SolutionPageByQry solutionPageByQry);
}
