package com.digitforce.aip.service.component;

import cn.hutool.core.util.StrUtil;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.ServingInstanceStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.mapper.ServingInstanceMapper;
import com.digitforce.aip.mapper.SolutionRunMapper;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.framework.context.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Slf4j
public class SchedulerTask {
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private IServingInstanceService servingInstanceService;
    @Resource
    private SolutionRunMapper solutionRunMapper;
    @Resource
    private ServingInstanceMapper servingInstanceMapper;
    @Resource
    private ISolutionService solutionService;
    @Resource
    private KubeflowPipelineService kubeflowPipelineService;

    /**
     * 每5秒检测一次solution-run的状态，并进行更新
     * TODO 后续将切换为quartz
     */
    @Scheduled(fixedRate = 20000)
    public void patrolSolutionRunStatus() {
        List<SolutionRun> solutionRunList = solutionRunMapper.getSomeRunningRecordsWithoutTenant(20);
        solutionRunList.forEach(record -> {
            RunStatusEnum status = kubeflowPipelineService.getStatus(record.getPRunId());
            if (status == RunStatusEnum.Running) {
                return;
            }
            SolutionRun updateRecord = new SolutionRun();
            updateRecord.setId(record.getId());
            updateRecord.setStatus(status);
            TenantContext.init(record.getTenantId());
            solutionRunService.updateById(updateRecord);
            if (record.getType() == SolutionRunTypeEnum.DEBUG) {
                Solution updateSolution = new Solution();
                updateSolution.setId(record.getSolutionId());
                switch (status) {
                    case Succeeded:
                        updateSolution.setStatus(SolutionStatusEnum.READY);
                        break;
                    case Failed:
                        updateSolution.setStatus(SolutionStatusEnum.ERROR);
                        break;
                    default:
                        log.error("unknown solutionRun status.[status={}]", record.getStatus());
                }
                solutionService.updateById(updateSolution);
            }
        });
        TenantContext.destroy();
    }

    @Scheduled(fixedRate = 20000)
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
