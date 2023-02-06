package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.digitforce.aip.entity.StarrocksTable;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

public class OlapSchemaMapperTest extends BaseTest {
    @Resource
    private OlapSchemaMapper olapSchemaMapper;

    @Test
    public void getTable() {
        List<StarrocksTable> starrocksTables =
                olapSchemaMapper.selectList(new LambdaQueryWrapper<StarrocksTable>().eq(StarrocksTable::getTableName,
                        "sample_jcbq"));

        System.out.println(starrocksTables);
    }

    @Test
    public void getOne() {
    }
}
