package com.digitforce.aip.mapper;

import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class SolutionMapperTest extends BaseTest {
    @Resource
    private SolutionMapper solutionMapper;
    @Resource
    private ISolutionService solutionService;


    @Test
    public void getSomeTuningRecordsWithoutTenant() {
        List<Solution> someTuningRecordsWithoutTenant = solutionMapper.getSomeTuningRecordsWithoutTenant(20);
        System.out.println(someTuningRecordsWithoutTenant);
    }
}