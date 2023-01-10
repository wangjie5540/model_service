package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.qry.ModelPackagePageByQry;
import com.digitforce.aip.entity.ModelPackage;
import com.digitforce.aip.mapper.ModelPackageMapper;
import com.digitforce.aip.service.IModelPackageService;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.tool.PageTool;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2023-01-09
 */
@Service
public class ModelPackageServiceImpl extends ServiceImpl<ModelPackageMapper, ModelPackage> implements IModelPackageService {
    @Override
    public PageView<ModelPackage> page(ModelPackagePageByQry modelPackagePageByQry) {
        QueryWrapper<ModelPackage> queryWrapper =
                new QueryWrapper<>(BeanUtil.toBean(modelPackagePageByQry.getClause(), ModelPackage.class));
        Map<String, Object> map = BeanUtil.beanToMap(modelPackagePageByQry.getLikeClause(), true, true);
        if (!Objects.isNull(map)) {
            map.forEach(queryWrapper::like);
        }
        Page<ModelPackage> page = PageUtil.page(modelPackagePageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }
}
