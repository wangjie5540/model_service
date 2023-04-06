package com.digitforce.aip.service.component;

import cn.hutool.core.util.StrUtil;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.dto.data.BestParameter;
import com.digitforce.aip.enums.AutoMLRunStatusEnum;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.ServingInstanceStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.mapper.ServingInstanceMapper;
import com.digitforce.aip.mapper.SolutionMapper;
import com.digitforce.aip.service.AutoMLService;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.aip.utils.ApplicationUtil;
import com.digitforce.framework.context.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SchedulerTask {
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private IServingInstanceService servingInstanceService;
    @Resource
    private SolutionMapper solutionMapper;
    @Resource
    private ServingInstanceMapper servingInstanceMapper;
    @Resource
    private ISolutionService solutionService;
    @Resource
    private KubeflowPipelineService kubeflowPipelineService;
    @Resource
    private AutoMLService autoMLService;

    @Scheduled(fixedRate = 20000)
    @Transactional(rollbackFor = Exception.class)
    public void patrolSolutionAutoMLStatus() {
        List<Solution> solutionList = solutionMapper.getSomeTuningRecordsWithoutTenant(20);
        solutionList.forEach(record -> {
            TenantContext.init(record.getTenantId());
            // 检测自动调参是否完成
            AutoMLRunStatusEnum status = autoMLService.getStatus(record.getARunId());
            if (status == AutoMLRunStatusEnum.Success) {
                List<BestParameter> autoMLResult = autoMLService.getAutoMLResult(record.getARunId());
                Map<String, Object> templateParams = solutionService.getById(record.getId()).getTemplateParams();
                autoMLResult.forEach(bestParameter -> templateParams.put(StrUtil.format("{}__{}", "model",
                                bestParameter.getName()),
                        bestParameter.getValue()));
                Solution solution = new Solution();
                solution.setId(record.getId());
                solution.setTemplateParams(templateParams);
                solution.setStatus(SolutionStatusEnum.EXECUTING);
                solutionService.updateById(solution);
                solutionRunService.createRun(record, SolutionRunTypeEnum.DEBUG, templateParams);
            } else if (status == AutoMLRunStatusEnum.Failed || status == AutoMLRunStatusEnum.unKnow) {
                Solution solution = new Solution();
                solution.setId(record.getId());
                solution.setStatus(SolutionStatusEnum.ERROR);
                solutionService.updateById(solution);
            }
        });
        TenantContext.destroy();
    }


    @Scheduled(fixedRate = 20000)
    @Transactional(rollbackFor = Exception.class)
    public void patrolServingInstanceStatus() {
        List<ServingInstance> servingInstances = servingInstanceMapper.getSomeRunningRecordsWithoutTenant(20);
        servingInstances.forEach(record -> {
            RunStatusEnum status = kubeflowPipelineService.getStatus(record.getPRunId());
            if (status == RunStatusEnum.Running) {
                return;
            }
            ServingInstance updateRecord = new ServingInstance();
            updateRecord.setId(record.getId());
            switch (status) {
                case Succeeded:
                    updateRecord.setStatus(ServingInstanceStatusEnum.FINISHED);
                    updateRecord.setResult(ApplicationUtil.generateServingResultUrl(record.getTenantId(),
                            record.getId()));
                    break;
                case Error:
                case Failed:
                    updateRecord.setStatus(ServingInstanceStatusEnum.ERROR);
                    break;
                default:
                    throw new RuntimeException(StrUtil.format("unknown run status.[status={}]", record.getStatus()));
            }
            TenantContext.init(record.getTenantId());
            servingInstanceService.updateById(updateRecord);
        });
        TenantContext.destroy();
    }
}
