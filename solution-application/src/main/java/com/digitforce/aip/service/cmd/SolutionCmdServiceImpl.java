package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionTriggerCmd;
import com.digitforce.aip.repository.SolutionRepository;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineCmdFacade;
import com.digitforce.bdp.operatex.core.consts.FailureStrategy;
import com.digitforce.bdp.operatex.core.consts.PlatformEnum;
import com.digitforce.bdp.operatex.core.consts.TaskCategory;
import com.digitforce.bdp.operatex.core.consts.TaskType;
import com.digitforce.bdp.operatex.core.consts.algorithm.AlgorithmTaskDefineDTO;
import com.digitforce.bdp.operatex.core.dto.TaskDefineDTO;
import com.digitforce.framework.operation.DefaultService;
import com.digitforce.framework.tool.ConvertTool;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 方案实施命令类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:59
 */
@Service
public class SolutionCmdServiceImpl extends DefaultService<Solution> implements SolutionCmdService {
    @Resource
    private SolutionRepository implementationRepository;
    @Resource
    private TaskDefineCmdFacade taskDefineCmdFacade;

    @Override
    public void add(SolutionAddCmd solutionAddCmd) {
        // TODO 参数校验
//        solutionValidator.validate(null);
        Solution solution = ConvertTool.convert(solutionAddCmd, Solution.class);
        implementationRepository.save(solution);
        // TODO 需要任务服务新增对应的任务类型定义
        TaskDefineDTO taskDefineDTO = new AlgorithmTaskDefineDTO();
        taskDefineDTO.setCategory(TaskCategory.BATCH);
        // TODO 后续将进行权限与用户对接
        taskDefineDTO.setCreateUserId(0L);
        taskDefineDTO.setCreateUserName("admin");
        taskDefineDTO.setDescription(solutionAddCmd.getDescription());
        taskDefineDTO.setFailureStrategy(FailureStrategy.END);
        taskDefineDTO.setName(solutionAddCmd.getName());
        // TODO 平台选择？
        taskDefineDTO.setPlatform(PlatformEnum.ALGOX);
        // TODO 多租户对接
        taskDefineDTO.setProjectId(10L);
        taskDefineDTO.setType(TaskType.ALGORITHM);
        // TODO 设置任务需要的参数，参数需要定义
        taskDefineDTO.setExtra("");
        taskDefineCmdFacade.addTask(taskDefineDTO);
    }

    @Override
    public void triggerRun(SolutionTriggerCmd implementationTriggerCmd) {
        Solution implementation = ConvertTool.convert(implementationTriggerCmd, Solution.class);
        implementationRepository.save(implementation);
        taskDefineCmdFacade.execute(implementation.getTaskId());
    }


    @Override
    public SolutionRepository getRepository() {
        return implementationRepository;
    }
}
