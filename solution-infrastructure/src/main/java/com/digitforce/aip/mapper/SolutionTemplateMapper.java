package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitforce.aip.domain.SolutionTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 方案模板mybatis plus mapper类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 15:47
 */
@Mapper
public interface SolutionTemplateMapper extends BaseMapper<SolutionTemplate> {
    @Update("UPDATE solution_template SET browse_count = browse_count + 1 WHERE deleted = 0 AND id = #{id}")
    int browseCountInc(@Param("id") Long id);

    @Update("UPDATE solution_template SET apply_count = apply_count + 1 WHERE deleted = 0 AND id = #{id}")
    int applyCountInc(@Param("id") Long id);
}
