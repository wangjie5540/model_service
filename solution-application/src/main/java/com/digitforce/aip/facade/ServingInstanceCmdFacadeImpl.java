package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.ServingInstanceAddCmd;
import com.digitforce.aip.dto.data.ServingInstanceDTO;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.mapper.StarrocksDDLMapper;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.aip.utils.OlapHelper;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ServingInstanceCmdFacadeImpl implements ServingInstanceCmdFacade {
    @Resource
    private ISolutionServingService solutionServingService;
    @Resource
    private IServingInstanceService servingInstanceService;
    @Resource
    private StarrocksDDLMapper starrocksDDLMapper;

    @Override
    public Result<ServingInstanceDTO> add(ServingInstanceAddCmd servingInstanceAddCmd) {
        SolutionServing solutionServing = solutionServingService.getById(servingInstanceAddCmd.getServingId());
        ServingInstance servingInstance = servingInstanceService.createAndRun(solutionServing);
        ServingInstanceDTO servingInstanceDTO = ConvertTool.convert(servingInstance, ServingInstanceDTO.class);
        starrocksDDLMapper.createPartition(OlapHelper.getScoreTableName(solutionServing.getSolutionId()),
                servingInstance.getId(), servingInstance.getId() + 1);
        return Result.success(servingInstanceDTO);
    }
}
