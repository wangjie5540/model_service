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
}