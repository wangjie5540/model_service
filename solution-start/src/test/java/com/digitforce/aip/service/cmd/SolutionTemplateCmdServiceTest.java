package com.digitforce.aip.service.cmd;

import com.digitforce.aip.mapper.SolutionTemplateMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionTemplateCmdServiceTest {
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;

    @Test
    public void browseCountInc() {
        int i = solutionTemplateMapper.browseCountInc(1546473725500678146L);
        System.out.println(i);
    }

    @Test
    public void applyCountInc() {
    }
}