package com.digitforce.aip.mapper;


import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

public class PredictDetailMapperTest extends BaseTest {
    @Resource
    private PredictResultMapper predictResultMapper;

    @Test
    public void topN() {
        Map<String, Object> total = predictResultMapper.topN("aip.score_251", 1645666375508107265L, 20L);
        System.out.println(total);
    }

    @Test
    public void topPercent() {
        Map<String, Object> total = predictResultMapper.topPercent("aip.score_241", 1645666375508107265L, 0.2);
        System.out.println(total);
    }

    @Test
    public void targetScore() {
        Map<String, Object> total = predictResultMapper.targetScore("aip.score_241", 1645666375508107265L, 0.2, 0.8);
        System.out.println(total);
    }

    @Test
    public void scoreRange() {
        Object total = predictResultMapper.getBaseRange("aip.score_241", 1645666375508107265L);
        System.out.println(total);
    }

    @Test
    public void targetScoreDistribution() {
        Object total = predictResultMapper.getTargetScoreDistribution("aip.score_251", 1645666375508107265L, 0.15, 0.8);
        System.out.println(total);
    }

    @Test
    public void getPredictDetailList() {
        Object total = predictResultMapper.getPredictDetailList("aip.score_251", 1645666375508107265L, 0.15, 0.8, 10
                , 0);
        System.out.println(total);
    }
}