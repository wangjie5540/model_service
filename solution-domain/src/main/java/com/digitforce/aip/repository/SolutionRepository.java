package com.digitforce.aip.repository;

import com.digitforce.aip.domain.Solution;
import com.digitforce.framework.operation.CrudOperation;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:54
 */
public interface SolutionRepository extends CrudOperation<Solution> {
    boolean isExist(Solution solution);
}
