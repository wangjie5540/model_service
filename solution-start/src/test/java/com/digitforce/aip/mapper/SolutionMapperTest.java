package com.digitforce.aip.mapper;

import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.framework.context.TenantContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionMapperTest {
    @Resource
    private SolutionMapper solutionMapper;

    @Before
    public void before() {
        TenantContext.init(10000);
    }

    @Test
    public void updateStatusByTaskId() {
        solutionMapper.updateStatusByTaskId(1541348442958237985L, 1L, SolutionStatusEnum.ONLINE);
    }

    @Test
    public void getStatusByTaskId() {
        System.out.println(solutionMapper.getStatusByTaskId(1541348442958237985L));
    }
}