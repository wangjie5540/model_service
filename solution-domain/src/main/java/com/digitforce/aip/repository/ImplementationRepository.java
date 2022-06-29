package com.digitforce.aip.repository;

import com.digitforce.aip.domain.Implementation;
import com.digitforce.framework.operation.CrudOperation;

/**
 * 方案实施持久化接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:54
 */
public interface ImplementationRepository extends CrudOperation<Implementation> {
    boolean isExist(Implementation implementation);
}
