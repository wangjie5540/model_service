package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.qry.ModelPageByQry;
import com.digitforce.aip.entity.Model;
import com.digitforce.aip.mapper.ModelMapper;
import com.digitforce.aip.service.IModelService;
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
public class ModelServiceImpl extends ServiceImpl<ModelMapper, Model> implements IModelService {

    @Override
    public PageView<Model> page(ModelPageByQry modelPageByQry) {
        QueryWrapper<Model> queryWrapper =
                new QueryWrapper<>(BeanUtil.toBean(modelPageByQry.getClause(), Model.class));
        Map<String, Object> map = BeanUtil.beanToMap(modelPageByQry.getLikeClause(), true, true);
        if (!Objects.isNull(map)) {
            map.forEach(queryWrapper::like);
        }
        Page<Model> page = PageUtil.page(modelPageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }
}
