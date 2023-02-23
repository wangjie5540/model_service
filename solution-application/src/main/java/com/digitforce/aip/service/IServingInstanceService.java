package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.qry.ServingInstancePageByQry;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.framework.api.dto.PageView;

/**
 * <p>
 * 服务实例表 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-28
 */
public interface IServingInstanceService extends IService<ServingInstance> {
    ServingInstance createAndRun(SolutionServing solutionServing);

    PageView<ServingInstance> page(ServingInstancePageByQry servingInstancePageByQry);
}
