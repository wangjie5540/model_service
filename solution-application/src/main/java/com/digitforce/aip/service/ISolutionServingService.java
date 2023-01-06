package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.framework.api.dto.PageView;

/**
 * <p>
 * 方案服务表 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-28
 */
public interface ISolutionServingService extends IService<SolutionServing> {
    PageView<SolutionServing> page(SolutionServingPageByQry solutionServingPageByQry);
}
