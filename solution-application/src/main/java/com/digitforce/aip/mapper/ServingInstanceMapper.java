package com.digitforce.aip.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitforce.aip.entity.ServingInstance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 服务实例表 Mapper 接口
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-28
 */
public interface ServingInstanceMapper extends BaseMapper<ServingInstance> {
    @InterceptorIgnore(tenantLine = "true")
    @Select("select * from serving_instance where status = 'PREDICTING' limit #{count}")
    List<ServingInstance> getSomeRunningRecordsWithoutTenant(@Param("count") int count);
}
