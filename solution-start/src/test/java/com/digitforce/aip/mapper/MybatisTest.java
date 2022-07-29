package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("dev")
public class MybatisTest {

    @Test
    public void sub() {
        QueryWrapper<?> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("aa");
        queryWrapper.eq("age", 1);
        queryWrapper.eq("name", "aaaa");
        System.out.println(queryWrapper.getTargetSql());
        System.out.println(queryWrapper.getSqlComment());
    }
}
