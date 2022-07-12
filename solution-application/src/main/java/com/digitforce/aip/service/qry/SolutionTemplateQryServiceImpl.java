package com.digitforce.aip.service.qry;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
import com.digitforce.aip.repository.SolutionTemplateRepository;
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
public class SolutionTemplateQryServiceImpl extends DefaultService<SolutionTemplate> implements SolutionTemplateQryService {
    @Resource
    private SolutionTemplateRepository solutionRepository;
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;

    @Override
    public SolutionTemplateRepository getRepository() {
        return solutionRepository;
    }

    @Override
    public PageView<SolutionTemplate> pageBy(SolutionTemplatePageByQry solutionTemplatePageByQry) {
        SolutionTemplate solutionTemplate = ConvertTool.convert(solutionTemplatePageByQry.getClause(),
                SolutionTemplate.class);
        // 需要对name进行模糊查询
        solutionTemplate.setName(null);
        PageQuery<SolutionTemplate> pageQuery = solutionTemplatePageByQry.build(d -> ConvertTool.convert(d,
                SolutionTemplate.class));
        QueryWrapper<SolutionTemplate> queryWrapper = new QueryWrapper<>(solutionTemplate);
        if (solutionTemplatePageByQry.getNameLike() != null) {
            queryWrapper.like("name", solutionTemplatePageByQry.getNameLike());
        }
        Page<SolutionTemplate> page = solutionTemplateMapper.selectPage(PageTool.page(pageQuery),
                queryWrapper);
        return PageView.of((int) page.getTotal(), page.getRecords());
    }
}
