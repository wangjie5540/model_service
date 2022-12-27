package com.digitforce.aip.facade;

import com.digitforce.aip.enums.AlgorithmEnum;
import com.digitforce.aip.enums.ApplySystemEnum;
import com.digitforce.aip.enums.BusinessEnum;
import com.digitforce.aip.enums.SceneStatusEnum;
import com.digitforce.aip.enums.SceneTypeEnum;
import com.digitforce.framework.api.dto.Result;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 枚举查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/19 17:34
 */
@RestController
public class EnumQryFacadeImpl implements EnumQryFacade {
    @Override
    public Result<List<BusinessEnum>> listBusinessEnums() {
        return Result.success(Lists.newArrayList(BusinessEnum.values()));
    }

    @Override
    public Result<List<SceneTypeEnum>> listSceneTypeEnums() {
        return Result.success(Lists.newArrayList(SceneTypeEnum.values()));
    }

    @Override
    public Result<List<SceneStatusEnum>> listSceneStatusEnums() {
        return Result.success(Lists.newArrayList(SceneStatusEnum.values()));
    }

    @Override
    public Result<List<ApplySystemEnum>> listApplySystemEnums() {
        return Result.success(Lists.newArrayList(ApplySystemEnum.values()));
    }

    @Override
    public Result<List<AlgorithmEnum>> listAlgorithmEnums() {
        return Result.success(Lists.newArrayList(AlgorithmEnum.values()));
    }
}
