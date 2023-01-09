package com.digitforce.aip.service;

import com.digitforce.aip.entity.dto.cmd.AutomlParams;
import com.digitforce.aip.entity.dto.data.BestParameter;
import com.digitforce.aip.enums.AutoMLRunStatusEnum;

import java.util.List;

/**
 * 自定调参服务接口类
 *
 * @author wangtonggui
 */
public interface AutoMLService {
    String createTask(AutomlParams automlParams);

    AutoMLRunStatusEnum getStatus(String runId);

    List<BestParameter> getAutoMLResult(String runId);
}
