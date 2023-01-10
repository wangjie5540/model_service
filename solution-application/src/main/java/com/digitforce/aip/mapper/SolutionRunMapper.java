package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitforce.aip.entity.SolutionRun;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 方案执行表 Mapper 接口
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
public interface SolutionRunMapper extends BaseMapper<SolutionRun> {
    @InterceptorIgnore(tenantLine = "true")
    @Select("select `id`,`tenant_id`,`solution_id`,`status`,`p_run_id`,`type` from solution_run where status = " +
        "'Running' limit #{count}")
    List<SolutionRun> getSomeRunningRecordsWithoutTenant(@Param("count") int count);

    @Select("select * from solution_run where solution_id = #{solutionId} order by create_time desc limit 1")
    SolutionRun getLatestRunBySolutionId(@Param("solutionId") Long solutionId);
}
