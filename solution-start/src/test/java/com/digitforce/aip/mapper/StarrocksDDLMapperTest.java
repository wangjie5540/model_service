package com.digitforce.aip.mapper;

import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;


public class StarrocksDDLMapperTest extends BaseTest {
    @Resource
    private StarrocksDDLMapper ddlMapper;

    @Test
    public void createUserScoreTable() {
        ddlMapper.createUserScoreTable("model_1");
    }

    @Test
    public void createPartition() {
        ddlMapper.createPartition("model_1", 2L, 3L);
    }

    @Test
    public void dropTable() {
        ddlMapper.dropTable("model_1");
    }

}