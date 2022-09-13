package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.FunctionDTO;
import com.digitforce.aip.dto.data.RelationDTO;
import com.digitforce.aip.enums.FunctionEnum;
import com.digitforce.aip.enums.RelationEnum;
import com.digitforce.framework.api.dto.Result;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;

/**
 * filter相关接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class FilterQryFacadeImpl implements FilterQryFacade {
    @Override
    public Result<FunctionDTO> functionListBy() {
        return Result.success(new FunctionDTO(Lists.newArrayList(FunctionEnum.values())));
    }

    @Override
    public Result<RelationDTO> relationListBy() {
        return Result.success(new RelationDTO(Lists.newArrayList(RelationEnum.values())));
    }
}
