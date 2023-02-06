package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitforce.aip.entity.Scene;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 场景表 Mapper 接口
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-09
 */
public interface SceneMapper extends BaseMapper<Scene> {
    @Update("update scene set solution_count = solution_count + 1 where id = #{id}")
    void increaseSolutionCount(Long id);

    @Update("update scene set solution_count = solution_count - 1 where id = #{id}")
    void decreaseSolutionCount(Long id);

    @Update("update scene set serving_count = serving_count + 1 where id = #{id}")
    void increaseServingCount(Long id);

    @Update("update scene set solution_count = serving_count - 1 where id = #{id}")
    void decreaseServingCount(Long id);
}
