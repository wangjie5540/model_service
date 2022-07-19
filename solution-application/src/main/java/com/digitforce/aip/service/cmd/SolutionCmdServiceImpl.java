package com.digitforce.aip.service.cmd;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.data.PipelineDataSource;
import com.digitforce.aip.enums.SolutionStatusEnum;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

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
        TriggerRunCmd triggerRunCmd = constructTriggerCmd(solutionAddCmd);
        taskDefineDTO.setExtra(GsonUtil.objectToString(triggerRunCmd));
        Long taskId = Long.parseLong(taskDefineCmdFacade.addTask(taskDefineDTO).getData().toString());
        Pipeline pipelineDetail = KubeflowHelper.getPipelineDetail(kubeflowProperties.getHost(),
                kubeflowProperties.getPort(), solutionAddCmd.getPipelineId());
        solution.setTaskId(taskId);
        PipelineDataSource dataSource = ConvertTool.convert(pipelineDetail.getDescription(), PipelineDataSource.class);
        solution.setDataSource(GsonUtil.objectToString(dataSource));
        solution.setSchedule(GlobalConstant.DEFAULT_CRON);
        solution.setStatus(SolutionStatusEnum.EXECUTING);
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

    private TriggerRunCmd constructTriggerCmd(SolutionAddCmd solutionAddCmd) {
        TriggerRunCmd triggerRunCmd = new TriggerRunCmd();
        triggerRunCmd.setName(solutionAddCmd.getPipelineName());
        triggerRunCmd.setExperimentId(GlobalConstant.DEFAULT_EXPERIMENT_ID);
        triggerRunCmd.setPipelineId(solutionAddCmd.getPipelineId());
        String startDate = getStartDateStr(solutionAddCmd.getTimeRange(), solutionAddCmd.getTimeUnit());
        String today = DateUtil.format(LocalDateTime.now(), "yyyy-MM-dd");
        List<Map<String, Object>> parameters = Lists.newArrayList();
        Map<String, Object> parameter = Maps.newHashMap();
        parameter.put("name", "train_data_start_date_str");
        parameter.put("value", startDate);
        parameters.add(parameter);
        parameter = Maps.newHashMap();
        parameter.put("name", "train_data_end_date_str");
        parameter.put("value", today);
        parameters.add(parameter);
        parameter = Maps.newHashMap();
        parameter.put("name", "run_datetime_str");
        parameter.put("value", today);
        parameters.add(parameter);
        triggerRunCmd.setPipelineParameters(parameters);
        return triggerRunCmd;
    }

    @Override
    public void execute(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution != null &&
                (solution.getStatus() == SolutionStatusEnum.NOT_EXECUTE
                        || solution.getStatus() == SolutionStatusEnum.FAILED
                        || solution.getStatus() == SolutionStatusEnum.FINISHED)) {
            Long taskInstanceId = taskDefineCmdFacade.execute(solution.getTaskId()).getData();
            solution = new Solution();
            solution.setId(id);
            if (taskInstanceId == null) {
                return;
            }
            solution.setStatus(SolutionStatusEnum.EXECUTING);
            solution.setTaskInstanceId(taskInstanceId);
            solutionRepository.upsert(solution);
        }
    }

    @Override
    public void stop(Long id) {
        Solution solution = solutionRepository.getById(id);
        if (solution != null && solution.getStatus() == SolutionStatusEnum.EXECUTING) {
            taskInstanceCmdFacade.stop(solution.getTaskInstanceId());
            solution = new Solution();
            solution.setStatus(SolutionStatusEnum.NOT_EXECUTE);
            solutionRepository.save(solution);
        }
    }


    @Override
    public SolutionRepository getRepository() {
        return solutionRepository;
    }

    private String getStartDateStr(Integer offset, ChronoUnit chronoUnit) {
        LocalDateTime localDateTime = LocalDateTimeUtil.offset(LocalDateTime.now(), -1L * offset, chronoUnit);
        localDateTime = LocalDateTimeUtil.beginOfDay(localDateTime);
        return DateUtil.format(localDateTime, "yyyy-MM-dd");
    }
}
