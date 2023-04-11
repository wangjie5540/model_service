package com.digitforce.aip.mapper;


import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Map;

public class OlapMapperTest extends BaseTest {
    @Resource
    private OlapMapper olapMapper;

    @Test
    public void selectOne() {
        Map<String, Object> one = olapMapper.selectOne("algorithm.sample_jcbq", "age, gender");
        System.out.println(one);
    }

    @Test
    public void topN() {
        Map<String, Object> total = olapMapper.topN("aip.score_241", 20L);
        System.out.println(total);
    }

    @Test
    public void topPercent() {
        Map<String, Object> total = olapMapper.topPercent("aip.score_241", 0.2);
        System.out.println(total);
    }

    @Test
    public void scoreRange() {
        Map<String, Object> total = olapMapper.scoreRange("aip.score_241", 0.2, 0.8);
        System.out.println(total);
    }
}