package com.digitforce.aip.repository;

import com.digitforce.aip.domain.Solution;
import com.digitforce.framework.operation.CrudOperation;

/**
 * 方案实施持久化接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:54
 */
public interface SolutionRepository extends CrudOperation<Solution> {
    boolean isExist(Solution implementation);
}
