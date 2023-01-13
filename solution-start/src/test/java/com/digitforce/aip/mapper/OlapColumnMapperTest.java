package com.digitforce.aip.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.digitforce.aip.entity.StarrocksColumn;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class OlapColumnMapperTest extends BaseTest {
    @Resource
    private OlapColumnMapper olapColumnMapper;

    @Test
    public void getColumn() {
        List<StarrocksColumn> starrocksColumns = olapColumnMapper
                .selectList(new LambdaQueryWrapper<StarrocksColumn>().eq(StarrocksColumn::getTableName, "sample_jcbq"));
        System.out.println(starrocksColumns);
    }
}