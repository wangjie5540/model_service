package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.qry.ModelPageByQry;
import com.digitforce.aip.entity.Model;
import com.digitforce.framework.api.dto.PageView;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2023-01-09
 */
public interface IModelService extends IService<Model> {
    PageView<Model> page(ModelPageByQry modelPageByQry);
}
