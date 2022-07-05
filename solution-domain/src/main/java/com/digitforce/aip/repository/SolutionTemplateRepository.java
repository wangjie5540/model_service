package com.digitforce.aip.repository;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.framework.operation.CrudOperation;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:54
 */
public interface SolutionTemplateRepository extends CrudOperation<SolutionTemplate> {
    boolean isExist(SolutionTemplate solutionTemplate);
}
