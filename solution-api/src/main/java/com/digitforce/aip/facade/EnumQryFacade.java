package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.enums.ApplySystemEnum;
import com.digitforce.aip.enums.BusinessEnum;
import com.digitforce.aip.enums.SceneStatusEnum;
import com.digitforce.aip.enums.SceneTypeEnum;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 枚举查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/19 17:29
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_ENUM_QRY)
public interface EnumQryFacade {
    @PostMapping("/solution/listBusinessEnums")
    @Operation(summary = "获取行业枚举列表", tags = CommonConst.SWAGGER_TAG_ENUM_QRY)
    Result<List<BusinessEnum>> listBusinessEnums();

    @PostMapping("/solution/listSceneTypeEnums")
    @Operation(summary = "获取场景类型枚举列表", tags = CommonConst.SWAGGER_TAG_ENUM_QRY)
    Result<List<SceneTypeEnum>> listSceneTypeEnums();

    @PostMapping("/solution/listSceneStatusEnums")
    @Operation(summary = "获取场景状态枚举列表", tags = CommonConst.SWAGGER_TAG_ENUM_QRY)
    Result<List<SceneStatusEnum>> listSceneStatusEnums();

    @PostMapping("/solution/listApplySystemEnums")
    @Operation(summary = "获取适用系统枚举列表", tags = CommonConst.SWAGGER_TAG_ENUM_QRY)
    Result<List<ApplySystemEnum>> listApplySystemEnums();
}
