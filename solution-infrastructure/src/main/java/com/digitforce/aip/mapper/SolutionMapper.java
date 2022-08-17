package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.enums.SolutionStatusEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 方案mybatis plus mapper类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 15:47
 */
@Mapper
public interface SolutionMapper extends BaseMapper<Solution> {
    @Update("UPDATE solution SET status = '${status}',  WHERE task_id = #{taskId} AND task_instance_id = " +
            "#{taskInstanceId} AND status != 'ONLINE' AND deleted = 0")
    int updateStatusByTaskId(@Param("taskId") Long taskId,
                             @Param("taskInstanceId") Long taskInstanceId, @Param("status") SolutionStatusEnum status);

    @Select("SELECT status FROM solution WHERE task_id = #{taskId} AND deleted = 0")
    SolutionStatusEnum getStatusByTaskId(@Param("taskId") Long taskId);
}
