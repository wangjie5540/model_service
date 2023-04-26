package com.digitforce.aip.mapper;

import com.digitforce.aip.test.BaseTest;
import com.digitforce.aip.utils.OlapHelper;
import org.junit.Test;

import javax.annotation.Resource;


public class StarrocksDDLMapperTest extends BaseTest {
    @Resource
    private StarrocksDDLMapper ddlMapper;

    @Test
    public void createUserScoreTable() {
        ddlMapper.createScoreTable(OlapHelper.getScoreTableName(11L));
    }

    @Test
    public void createPartition() {
        ddlMapper.createPartition("aip.score_263", 15L, 16L);
    }

    @Test
    public void dropTable() {
        ddlMapper.dropTable("model_1");
    }
}