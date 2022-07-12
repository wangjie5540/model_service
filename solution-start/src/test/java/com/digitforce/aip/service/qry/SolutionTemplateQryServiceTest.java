package com.digitforce.aip.service.qry;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
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

    @Test
    public void pageBy() {
        SolutionTemplate solutionTemplate = new SolutionTemplate();
        solutionTemplate.setApplyCount(1);
        QueryWrapper<SolutionTemplate> queryWrapper = new QueryWrapper<>(solutionTemplate);
        queryWrapper.like("name", "描述");
        SolutionTemplatePageByQry solutionTemplatePageByQry = new SolutionTemplatePageByQry();
        solutionTemplatePageByQry.setPageNum(1);
        solutionTemplatePageByQry.setPageSize(10);
        PageQuery<SolutionTemplate> pageQuery = solutionTemplatePageByQry.build(d -> ConvertTool.convert(d,
                SolutionTemplate.class));
        System.out.println(solutionTemplateMapper.selectPage(PageTool.page(pageQuery), queryWrapper));
    }
}