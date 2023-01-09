package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitforce.aip.entity.Solution;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 方案表 Mapper 接口
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
public interface SolutionMapper extends BaseMapper<Solution> {
    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from solution_run where status = 'TUNING' limit #{count}")
    List<Solution> getSomeTuningRecordsWithoutTenant(@Param("count") int count);
}
