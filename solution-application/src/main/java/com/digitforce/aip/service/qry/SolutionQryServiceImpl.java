package com.digitforce.aip.service.qry;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.aip.mapper.SolutionMapper;
import com.digitforce.aip.repository.SolutionRepository;
import com.digitforce.framework.api.dto.PageQuery;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.operation.DefaultService;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 方案查询服务接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:59
 */
@Service
public class SolutionQryServiceImpl extends DefaultService<Solution> implements SolutionQueryService {
    @Resource
    private SolutionRepository solutionRepository;
    @Resource
    private SolutionMapper solutionMapper;

    @Override
    public SolutionRepository getRepository() {
        return solutionRepository;
    }

    @Override
    public PageView<Solution> pageBy(SolutionPageByQry solutionPageByQry) {
        Solution solution = ConvertTool.convert(solutionPageByQry.getClause(),
                Solution.class);
        // 需要对name进行模糊查询
        QueryWrapper<Solution> queryWrapper;
        if (solution != null) {
            solution.setName(null);
        }
        queryWrapper = new QueryWrapper<>(solution);
        PageQuery<Solution> pageQuery = solutionPageByQry.build(d -> ConvertTool.convert(d,
                Solution.class));
        if (solutionPageByQry.getNameLike() != null) {
            queryWrapper.like("name", solutionPageByQry.getNameLike());
        }
        Page<Solution> page = solutionMapper.selectPage(PageTool.page(pageQuery),
                queryWrapper);
        return PageView.of((int) page.getTotal(), page.getRecords());
    }
}
