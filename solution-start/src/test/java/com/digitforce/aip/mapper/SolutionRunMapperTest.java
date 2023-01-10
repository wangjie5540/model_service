package com.digitforce.aip.mapper;


import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * mapper测试单元
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2023/01/08 14:21
 */
@Slf4j
public class SolutionRunMapperTest extends BaseTest {
    @Resource
    private SolutionRunMapper solutionRunMapper;

    @Test
    public void getLatestRunBySolutionId() {
        SolutionRun solutionRun = solutionRunMapper.getLatestRunBySolutionId(36L);
        log.info("solutionRun: {}", solutionRun);
        Assert.assertNotNull(solutionRun);
    }
}