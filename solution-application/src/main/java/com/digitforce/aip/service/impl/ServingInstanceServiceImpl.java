package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.qry.ServingInstancePageByQry;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.mapper.ServingInstanceMapper;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.tool.PageTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 服务实例表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-28
 */
@Service
@Slf4j
public class ServingInstanceServiceImpl extends ServiceImpl<ServingInstanceMapper, ServingInstance> implements IServingInstanceService {
    @Override
    public PageView<ServingInstance> page(ServingInstancePageByQry servingInstancePageByQry) {
        QueryWrapper<ServingInstance> queryWrapper =
                new QueryWrapper<>(BeanUtil.toBean(servingInstancePageByQry.getClause(), ServingInstance.class));
        Map<String, Object> map = BeanUtil.beanToMap(servingInstancePageByQry.getLikeClause(), true, true);
        if (!Objects.isNull(map)) {
            map.forEach(queryWrapper::like);
        }
        Page<ServingInstance> page = PageUtil.page(servingInstancePageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }
}
