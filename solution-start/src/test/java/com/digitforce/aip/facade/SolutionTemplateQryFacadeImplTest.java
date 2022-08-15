package com.digitforce.aip.facade;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.enums.TemplateStatusEnum;
import com.digitforce.aip.service.qry.SolutionTemplateQryService;
import com.digitforce.framework.context.TenantContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionTemplateQryFacadeImplTest {
    @Resource
    private SolutionTemplateQryService solutionTemplateQryService;

    @Before
    public void before() {
        TenantContext.init(10000);
    }

    @Test
    public void listBy() {
        SolutionTemplate template = new SolutionTemplate();
        template.setStatus(TemplateStatusEnum.ONLINE);
        List<SolutionTemplate> solutionTemplateList = solutionTemplateQryService.listBy(template);
        System.out.println(solutionTemplateList);
    }
}