package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.dto.cmd.SolutionServingDeleteCmd;
import com.digitforce.framework.api.dto.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

/**
 * 策略命令接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:40
 */
@RestController
public class SolutionServingCmdFacadeImpl implements SolutionServingCmdFacade {
//    @Resource
//    private KafkaTemplate<String, Object> kafkaTemplate;
//    @Resource
//    private KafkaProperties kafkaProperties;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result add(SolutionServingAddCmd solutionServingAddCmd) {
//        SolutionServing solutionServing = ConvertTool.convert(solutionServingAddCmd, SolutionServing.class);
//        if (solutionServing.getSelection() != null) {
//            solutionServing.getSelection().forEach(
//                tableSelection -> tableSelection.setFilterSql(ApplicationUtil.filterToSql(tableSelection.getFilter())));
//        }
//        solutionServingCmdService.save(solutionServing);
//        SolutionServingStatusDTO solutionServingStatusDTO = new SolutionServingStatusDTO();
//        solutionServingStatusDTO.setStatus(StatusChangeEnum.ADD);
//        solutionServingStatusDTO.setSolutionServing(solutionServing);
//        kafkaTemplate.send(kafkaProperties.getSolutionServingStatusTopic(), solutionServingStatusDTO);
//        return Result.success(solutionServing.getId());
        return null;
    }

    @Override
    public Result delete(SolutionServingDeleteCmd solutionServingDeleteCmd) {
//        SolutionServing solutionServing = solutionServingCmdService.getById(solutionServingDeleteCmd.getServingId());
//        solutionServingCmdService.removeById(solutionServingDeleteCmd.getServingId());
//        SolutionServingStatusDTO solutionServingStatusDTO = new SolutionServingStatusDTO();
//        solutionServingStatusDTO.setStatus(StatusChangeEnum.DELETE);
//        solutionServingStatusDTO.setSolutionServing(solutionServing);
//        kafkaTemplate.send(kafkaProperties.getSolutionServingStatusTopic(), solutionServingStatusDTO);
//        return Result.success();
        return null;
    }
}
