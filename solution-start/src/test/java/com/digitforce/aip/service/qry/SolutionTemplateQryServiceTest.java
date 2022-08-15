package com.digitforce.aip.service.qry;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineCmdFacade;
import com.digitforce.framework.api.dto.PageQuery;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionTemplateQryServiceTest {
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;
    @Resource
    private TaskDefineCmdFacade taskDefineCmdFacade;

    @Test
    public void pageBy() {
        QueryWrapper<SolutionTemplate> queryWrapper = new QueryWrapper<>();
        SolutionTemplatePageByQry solutionTemplatePageByQry = new SolutionTemplatePageByQry();
        solutionTemplatePageByQry.setPageNum(1);
        solutionTemplatePageByQry.setPageSize(10);
        PageQuery<SolutionTemplate> pageQuery = solutionTemplatePageByQry.build(d -> ConvertTool.convert(d,
                SolutionTemplate.class));
        Page<SolutionTemplate> solutionTemplatePage = solutionTemplateMapper.selectPage(PageTool.page(pageQuery),
                queryWrapper);
        System.out.println(solutionTemplatePage);
    }
}