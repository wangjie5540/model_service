package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.PredictResultDTO;
import com.digitforce.aip.dto.data.ServingInstanceDTO;
import com.digitforce.aip.dto.qry.GetPredictResultQry;
import com.digitforce.aip.dto.qry.ServingInstanceGetByIdQry;
import com.digitforce.aip.dto.qry.ServingInstancePageByQry;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.mapper.OlapMapper;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.utils.OlapHelper;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 服务实例查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
@Slf4j
public class ServingInstanceQryFacadeImpl implements ServingInstanceQryFacade {
    @Resource
    private IServingInstanceService servingInstanceService;
    @Resource
    private OlapMapper olapMapper;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public Result<PageView<ServingInstanceDTO>> pageBy(ServingInstancePageByQry servingInstancePageByQry) {
        PageView<ServingInstance> solutionPageView = servingInstanceService.page(servingInstancePageByQry);
        PageView<ServingInstanceDTO> solutionDTOPageView = PageTool.pageView(solutionPageView,
                ServingInstanceDTO.class);
        return Result.success(solutionDTOPageView);
    }

    @Override
    public Result<ServingInstanceDTO> getById(ServingInstanceGetByIdQry servingInstanceGetByIdQry) {
        ServingInstance servingInstance = servingInstanceService.getById(servingInstanceGetByIdQry.getId());
        return Result.success(ConvertTool.convert(servingInstance, ServingInstanceDTO.class));
    }

    @Override
    @SneakyThrows
    public Result<PredictResultDTO> getPredictResult(GetPredictResultQry getPredictResultQry) {
        ServingInstance servingInstance = servingInstanceService.getById(getPredictResultQry.getInstanceId());
//        String tableName = OlapHelper.getScoreTableName(servingInstance.getSolutionId());
        String tableName = OlapHelper.getScoreTableName(241L);
        switch (getPredictResultQry.getScoreRangeType()) {
            case TOP_N:
                Long n = Long.parseLong(getPredictResultQry.getValues().get(0).toString());
                String str = objectMapper.writeValueAsString(olapMapper.topN(tableName, n));
                log.info(str);

                PredictResultDTO predictResultDTO = objectMapper.readValue(str, PredictResultDTO.class);
                return Result.success(predictResultDTO);
            case TOP_PERCENT:

        }
        return null;
    }
}
