package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.mapper.SolutionServingMapper;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.tool.PageTool;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 方案服务表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-28
 */
@Service
public class SolutionServingServiceImpl extends ServiceImpl<SolutionServingMapper, SolutionServing>
    implements ISolutionServingService {
    @Override
    public PageView<SolutionServing> page(SolutionServingPageByQry solutionServingPageByQry) {
        QueryWrapper<SolutionServing> queryWrapper =
            new QueryWrapper<>(BeanUtil.toBean(solutionServingPageByQry.getClause(), SolutionServing.class));
        Map<String, Object> map = BeanUtil.beanToMap(solutionServingPageByQry.getLikeClause(), false, true);
        if (!Objects.isNull(map)) {
            map.forEach(queryWrapper::like);
        }
        Page<SolutionServing> page = PageUtil.page(solutionServingPageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }
}
