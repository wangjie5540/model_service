package com.digitforce.aip.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.digitforce.aip.entity.StarrocksTable;

/**
 * 查询starrocks使用的mapper
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/25 12:16
 */
@InterceptorIgnore(tenantLine = "true")
@DS("olap")
public interface OlapSchemaMapper extends BaseMapper<StarrocksTable> {
}
