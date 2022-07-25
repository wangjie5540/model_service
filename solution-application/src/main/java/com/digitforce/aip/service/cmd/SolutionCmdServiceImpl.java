package com.digitforce.aip.service.cmd;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.mapper.SolutionMapper;
import com.digitforce.aip.model.Pipeline;
import com.digitforce.aip.model.TriggerRunCmd;
import com.digitforce.aip.repository.SolutionRepository;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineCmdFacade;
import com.digitforce.bdp.operatex.core.api.taskInstance.TaskInstanceCmdFacade;
import com.digitforce.bdp.operatex.core.consts.FailureStrategy;
import com.digitforce.bdp.operatex.core.consts.PlatformEnum;
import com.digitforce.bdp.operatex.core.consts.TaskCategory;
import com.digitforce.bdp.operatex.core.consts.TaskType;
import com.digitforce.bdp.operatex.core.consts.algorithm.AlgorithmTaskDefineDTO;
import com.digitforce.bdp.operatex.core.dto.TaskDefineDTO;
import com.digitforce.framework.operation.DefaultService;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.util.GsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private SolutionRepository solutionRepository;
    @Resource
    private TaskDefineCmdFacade taskDefineCmdFacade;
    @Resource
    private TaskInstanceCmdFacade taskInstanceCmdFacade;
    @Resource
    private KubeflowProperties kubeflowProperties;
    @Resource
    private SolutionMapper solutionMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void add(SolutionAddCmd solutionAddCmd) {
        // TODO 参数校验
//        solutionValidator.validate(null);
        Solution solution = ConvertTool.convert(solutionAddCmd, Solution.class);
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
        taskDefineDTO.setProjectId(GlobalConstant.DEFAULT_PROJECT_ID);
        taskDefineDTO.setType(TaskType.ALGORITHM);
        taskDefineDTO.setIsRunNow(0);
        solution.setId(IdWorker.getId());
        TriggerRunCmd triggerRunCmd = constructTriggerCmd(solution.getId(), solutionAddCmd);
        taskDefineDTO.setExtra(GsonUtil.objectToString(triggerRunCmd));
        Long taskId = Long.parseLong(taskDefineCmdFacade.addTask(taskDefineDTO).getData().toString());
        Pipeline pipelineDetail = KubeflowHelper.getPipelineDetail(kubeflowProperties.getHost(),
                kubeflowProperties.getPort(), solutionAddCmd.getPipelineId());
        solution.setTaskId(taskId);
//        PipelineDataSource dataSource = ConvertTool.convert(pipelineDetail.getDescription(), PipelineDataSource
//        .class);
//        solution.setDataSource(GsonUtil.objectToString(dataSource));
        solution.setSchedule(GlobalConstant.DEFAULT_CRON);
        if (solutionAddCmd.getExecuteNow() == 1) {
            Long taskInstanceId = taskDefineCmdFacade.execute(taskId).getData();
            solution.setStatus(SolutionStatusEnum.EXECUTING);
            solution.setTaskInstanceId(taskInstanceId);
        }
        solutionRepository.save(solution);
    }

    @Override
    public void on(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution.getStatus() == SolutionStatusEnum.FINISHED) {
            solution = new Solution();
            solution.setId(id);
            solution.setStatus(SolutionStatusEnum.ONLINE);
            solutionRepository.modifyById(solution);
        }
    }

    @Override
    public void off(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution.getStatus() == SolutionStatusEnum.ONLINE) {
            solution = new Solution();
            solution.setId(id);
            solution.setStatus(SolutionStatusEnum.FINISHED);
            solutionRepository.modifyById(solution);
        }
    }

    private TriggerRunCmd constructTriggerCmd(Long solutionId, SolutionAddCmd solutionAddCmd) {
        TriggerRunCmd triggerRunCmd = new TriggerRunCmd();
        triggerRunCmd.setName(solutionAddCmd.getPipelineName());
        triggerRunCmd.setExperimentId(GlobalConstant.DEFAULT_EXPERIMENT_ID);
        triggerRunCmd.setPipelineId(solutionAddCmd.getPipelineId());
        triggerRunCmd.setTimeRange(solutionAddCmd.getTimeRange());
        triggerRunCmd.setTimeUnit(solutionAddCmd.getTimeUnit());
        triggerRunCmd.setSolutionId(solutionId);
        return triggerRunCmd;
    }

    @Override
    public void execute(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution != null && (solution.getStatus() == SolutionStatusEnum.NOT_EXECUTE
                || solution.getStatus() == SolutionStatusEnum.FAILED
                || solution.getStatus() == SolutionStatusEnum.FINISHED)) {
            Long taskInstanceId = taskDefineCmdFacade.execute(solution.getTaskId()).getData();
            solution = new Solution();
            solution.setId(id);
            if (taskInstanceId == null) {
                return;
            }
//            solution.setStatus(SolutionStatusEnum.EXECUTING);
            solution.setTaskInstanceId(taskInstanceId);
            solutionRepository.upsert(solution);
        }
    }

    @Override
    public void onExecuting(Long taskId) {
        solutionMapper.updateStatusByTaskId(taskId, SolutionStatusEnum.EXECUTING);
    }

    @Override
    public void onFinished(Long taskId) {
        SolutionStatusEnum status = solutionMapper.getStatusByTaskId(taskId);
        if (status != SolutionStatusEnum.ONLINE) {
            solutionMapper.updateStatusByTaskId(taskId, SolutionStatusEnum.FINISHED);
        }
    }

    @Override
    public void stop(Long id) {
        Solution solution = solutionRepository.getById(id);
        taskInstanceCmdFacade.stop(solution.getTaskInstanceId());
    }

    @Override
    public void onStopping(Long taskId) {
        solutionMapper.updateStatusByTaskId(taskId, SolutionStatusEnum.STOPPING);
    }

    @Override
    public void onFailed(Long taskId) {
        solutionMapper.updateStatusByTaskId(taskId, SolutionStatusEnum.FAILED);
    }

    @Override
    public void delete(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution.getStatus() == SolutionStatusEnum.ONLINE || solution.getStatus() == SolutionStatusEnum.EXECUTING) {
            throw new RuntimeException("solution is using");
        }
        solutionRepository.removeById(id);
    }

    @Override
    public SolutionRepository getRepository() {
        return solutionRepository;
    }
}
