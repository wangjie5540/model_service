package com.digitforce.aip.service;

import com.digitforce.aip.entity.dto.cmd.AutomlParams;

/**
 * 自定调参服务接口类
 *
 * @author wangtonggui
 */
public interface AutoMLService {
    String createTask(AutomlParams automlParams);
}
