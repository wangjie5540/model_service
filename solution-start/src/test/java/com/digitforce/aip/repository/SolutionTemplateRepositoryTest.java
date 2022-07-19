package com.digitforce.aip.repository;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.framework.api.dto.PageQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionTemplateRepositoryTest {
    @Resource
    private SolutionTemplateRepository solutionRepository;
    @Resource
    private SolutionTemplateRepositoryImpl impl;

    @Test
    public void isExist() {
        SolutionTemplate solution = new SolutionTemplate();
        solution.setId(1536194035278557185L);
        System.out.println(solutionRepository.isExist(solution));
    }

    @Test
    public void pageBy() {
        PageQuery pageQuery = new PageQuery<>();
        pageQuery.setPageNum(2);
        pageQuery.setPageSize(10);
        pageQuery.setCountable(true);
        System.out.println(solutionRepository.pageBy(pageQuery));
    }
}