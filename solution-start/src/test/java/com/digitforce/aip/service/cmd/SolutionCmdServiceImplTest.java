package com.digitforce.aip.service.cmd;

import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.model.TriggerRunCmd;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineCmdFacade;
import com.digitforce.bdp.operatex.core.consts.FailureStrategy;
import com.digitforce.bdp.operatex.core.consts.PlatformEnum;
import com.digitforce.bdp.operatex.core.consts.TaskCategory;
import com.digitforce.bdp.operatex.core.consts.TaskType;
import com.digitforce.bdp.operatex.core.consts.algorithm.AlgorithmTaskDefineDTO;
import com.digitforce.bdp.operatex.core.dto.TaskDefineDTO;
import com.digitforce.framework.util.GsonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionCmdServiceImplTest {
    @Resource
    private TaskDefineCmdFacade taskDefineCmdFacade;

    @Test
    public void taskAdd() {
        // TODO 需要任务服务新增对应的任务类型定义
        TaskDefineDTO taskDefineDTO = new AlgorithmTaskDefineDTO();
        taskDefineDTO.setCategory(TaskCategory.BATCH);
        // TODO 后续将进行权限与用户对接
        taskDefineDTO.setCreateUserId(0L);
        taskDefineDTO.setCreateUserName("admin");
        taskDefineDTO.setDescription("测试任务描述");
        taskDefineDTO.setFailureStrategy(FailureStrategy.END);
        taskDefineDTO.setName("算法测试任务16");
        // TODO 平台选择？
        taskDefineDTO.setPlatform(PlatformEnum.ALGOX);
        // TODO 多租户对接
        taskDefineDTO.setProjectId(10L);
        taskDefineDTO.setType(TaskType.ALGORITHM);
        TriggerRunCmd triggerRunCmd = new TriggerRunCmd();
        triggerRunCmd.setName("for_test");
        triggerRunCmd.setExperimentId(GlobalConstant.DEFAULT_EXPERIMENT_ID);
        triggerRunCmd.setPipelineId("268eacf1-126f-4e64-9911-6db5f6ca5a2f");
        List<Map<String, Object>> parameters = Lists.newArrayList();
        Map<String, Object> parameter = Maps.newHashMap();
        parameter.put("name", "train_data_start_date_str");
        parameter.put("value", "2020-06-20");
        parameters.add(parameter);
        parameter = Maps.newHashMap();
        parameter.put("name", "train_data_end_date_str");
        parameter.put("value", "2022-06-28");
        parameters.add(parameter);
        parameter = Maps.newHashMap();
        parameter.put("name", "run_datetime_str");
        parameter.put("value", "2022-06-28");
        parameters.add(parameter);
        triggerRunCmd.setPipelineParameters(parameters);
        // TODO 设置任务需要的参数，参数需要定义
        taskDefineDTO.setExtra(GsonUtil.objectToString(triggerRunCmd));
        taskDefineCmdFacade.addTask(taskDefineDTO);
    }
}