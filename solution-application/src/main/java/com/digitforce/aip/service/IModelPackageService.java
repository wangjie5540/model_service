package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.qry.ModelPackagePageByQry;
import com.digitforce.aip.entity.ModelPackage;
import com.digitforce.framework.api.dto.PageView;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2023-01-09
 */
public interface IModelPackageService extends IService<ModelPackage> {
    PageView<ModelPackage> page(ModelPackagePageByQry modelPackagePageByQry);
}
