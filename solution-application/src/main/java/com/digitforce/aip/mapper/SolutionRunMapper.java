package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitforce.aip.entity.SolutionRun;

/**
 * <p>
 * 方案执行表 Mapper 接口
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
@InterceptorIgnore(tenantLine = "true")
public interface SolutionRunMapper extends BaseMapper<SolutionRun> {

}
