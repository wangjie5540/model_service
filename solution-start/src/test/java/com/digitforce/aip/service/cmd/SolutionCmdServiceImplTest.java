package com.digitforce.aip.service.cmd;

import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.model.TriggerRunCmd;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineCmdFacade;
import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineQryFacade;
import com.digitforce.bdp.operatex.core.consts.FailureStrategy;
import com.digitforce.bdp.operatex.core.consts.PlatformEnum;
import com.digitforce.bdp.operatex.core.consts.TaskCategory;
import com.digitforce.bdp.operatex.core.consts.TaskType;
import com.digitforce.bdp.operatex.core.consts.algorithm.AlgorithmTaskDefineDTO;
import com.digitforce.bdp.operatex.core.dto.TaskDefineDTO;
import com.digitforce.bdp.operatex.core.vo.TaskDefineVO;
import com.digitforce.component.dict.api.client.DictEntryQryFacade;
import com.digitforce.component.dict.api.dto.DictEntryDTO;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.util.GsonUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Before;
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
    @Resource
    private TaskDefineQryFacade taskDefineQryFacade;
    @Resource
    private SolutionCmdService solutionCmdService;
    @Resource
    private DictEntryQryFacade dictEntryQryFacade;
    @Resource
    private KubeflowProperties kubeflowProperties;

    @Before
    public void before() {
        TenantContext.init(10000);
    }

    @Test
    public void taskAdd() {
        TenantContext.init(10000);
        // TODO 需要任务服务新增对应的任务类型定义
        TaskDefineDTO taskDefineDTO = new AlgorithmTaskDefineDTO();
        taskDefineDTO.setCategory(TaskCategory.BATCH);
        // TODO 后续将进行权限与用户对接
        taskDefineDTO.setCreateUserId(0L);
        taskDefineDTO.setCreateUserName("admin");
        taskDefineDTO.setDescription("测试任务描述");
        taskDefineDTO.setFailureStrategy(FailureStrategy.END);
        taskDefineDTO.setName("算法测试任务60");
        // TODO 平台选择？
        taskDefineDTO.setPlatform(PlatformEnum.ALGOX);
        // TODO 多租户对接
        taskDefineDTO.setProjectId(10L);
        taskDefineDTO.setType(TaskType.ALGORITHM);
        TriggerRunCmd triggerRunCmd = new TriggerRunCmd();
        triggerRunCmd.setName("for_test");
        triggerRunCmd.setExperimentId(kubeflowProperties.getExperimentId());
        triggerRunCmd.setPipelineId("3c094336-21aa-4b54-8833-2bf10e7d2085");
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
        // TODO 设置任务需要的参数，参数需要定义
        taskDefineDTO.setExtra(GsonUtil.objectToString(triggerRunCmd));
        taskDefineCmdFacade.addTask(taskDefineDTO);
    }

    @Test
    public void add() {
    }

    @Test
    public void execute() {
        taskDefineCmdFacade.execute(1541348442958237985L);
    }

    @Test
    public void stop() {
        taskDefineCmdFacade.delete(1541348442958237985L);
    }

    @Test
    public void update() {
        TaskDefineVO taskDefineVOById = taskDefineQryFacade.getTaskDefineVOById(1541348442958238005L).getData();
        System.out.println(taskDefineVOById);
    }

    @Test
    public void getByIdTest() {
        Result<TaskDefineVO> taskDefineVOById = taskDefineQryFacade.getTaskDefineVOById(1541348442958238012L);
        System.out.println(taskDefineVOById);
    }

    @Test
    public void dictTest() {
        DictEntryDTO dictEntryDTO = new DictEntryDTO();
        dictEntryDTO.setTypeKey("goods_property");
        System.out.println(dictEntryQryFacade.listByTypeKey(dictEntryDTO));
        //        System.out.println(dictEntryQryFacade.getBy(dictEntryDTO));
    }
}