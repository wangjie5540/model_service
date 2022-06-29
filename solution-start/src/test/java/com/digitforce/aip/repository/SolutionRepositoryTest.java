package com.digitforce.aip.repository;

import com.digitforce.aip.domain.Solution;
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
public class SolutionRepositoryTest {
    @Resource
    private SolutionRepository solutionRepository;
    @Resource
    private SolutionRepositoryImpl impl;

    @Test
    public void isExist() {
        Solution solution = new Solution();
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