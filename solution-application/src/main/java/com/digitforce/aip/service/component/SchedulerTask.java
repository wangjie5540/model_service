package com.digitforce.aip.service.component;

import cn.hutool.core.util.StrUtil;
import com.digitforce.aip.config.HdfsProperties;
import com.digitforce.aip.config.ModelManagementProperties;
import com.digitforce.aip.dto.data.ModelDesc;
import com.digitforce.aip.entity.Model;
import com.digitforce.aip.entity.ModelPackage;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.entity.dto.data.BestParameter;
import com.digitforce.aip.enums.AutoMLRunStatusEnum;
import com.digitforce.aip.enums.ResourceTypeEnum;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.ServingInstanceStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.mapper.ServingInstanceMapper;
import com.digitforce.aip.mapper.SolutionMapper;
import com.digitforce.aip.mapper.SolutionRunMapper;
import com.digitforce.aip.service.AutoMLService;
import com.digitforce.aip.service.IModelPackageService;
import com.digitforce.aip.service.IModelService;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.framework.context.TenantContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SchedulerTask {
    //    @Resource
//    // TODO 后续再使用，担心影响全局
//    private ObjectMapper objectMapper;
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private IServingInstanceService servingInstanceService;
    @Resource
    private SolutionRunMapper solutionRunMapper;
    @Resource
    private SolutionMapper solutionMapper;
    @Resource
    private ServingInstanceMapper servingInstanceMapper;
    @Resource
    private ISolutionService solutionService;
    @Resource
    private KubeflowPipelineService kubeflowPipelineService;
    @Resource
    private HdfsProperties hdfsProperties;
    @Resource
    private HdfsComponent hdfsComponent;
    @Resource
    private ModelManagementProperties modelManagementProperties;
    @Resource
    private IModelPackageService modelPackageService;
    @Resource
    private IModelService modelService;
    @Resource
    private AutoMLService autoMLService;

    /**
     * 每5秒检测一次solution-run的状态，并进行更新
     * TODO 后续将切换为quartz
     */
    @Scheduled(fixedRate = 20000)
    public void patrolSolutionRunStatus() {
        List<SolutionRun> solutionRunList = solutionRunMapper.getSomeRunningRecordsWithoutTenant(20);
        solutionRunList.forEach(record -> {
            RunStatusEnum status = kubeflowPipelineService.getStatus(record.getPRunId());
            SolutionRun updateRecord = new SolutionRun();
            switch (status) {
                case Running:
                    return;
                case Succeeded:
                    ModelPackage modelPackage = parseModelPackage(record);
                    updateRecord.setPackageId(modelPackage.getId());
                    break;
                case Error:
                case Failed:
                    break;
                default:
                    log.error("未知的solution-run状态: {}", status);
            }
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
    public void patrolSolutionAutoMlStatus() {
        List<Solution> solutionList = solutionMapper.getSomeTuningRecordsWithoutTenant(20);
        solutionList.forEach(record -> {
            // 检测自动调参是否完成
            AutoMLRunStatusEnum status = autoMLService.getStatus(record.getARunId());
            switch (status) {
                case Success:
                    // TODO 保存最优模型
                    List<BestParameter> autoMLResult = autoMLService.getAutoMLResult(record.getARunId());
                    Map<String, Object> templateParams = record.getTemplateParams();
                    autoMLResult.forEach(bestParameter -> {
                        templateParams.put(StrUtil.format("{}__{}", "model", bestParameter.getName()),
                                bestParameter.getValue());
                    });
                    Solution solution = new Solution();
                    solution.setId(record.getId());
                    solution.setTemplateParams(templateParams);
                    break;
                default:
                    return;
            }
        });
//        solutionList.forEach(record -> {
//            // 检测自动调参任务状态
//
//            RunStatusEnum status = kubeflowPipelineService.getStatus(record.getPRunId());
//            SolutionRun updateRecord = new SolutionRun();
//            switch (status) {
//                case Running:
//                    return;
//                case Succeeded:
//                    ModelPackage modelPackage = parseModelPackage(record);
//                    updateRecord.setPackageId(modelPackage.getId());
//                    break;
//                case Error:
//                case Failed:
//                    break;
//                default:
//                    log.error("未知的solution-run状态: {}", status);
//            }
//            updateRecord.setId(record.getId());
//            updateRecord.setStatus(status);
//            TenantContext.init(record.getTenantId());
//            solutionRunService.updateById(updateRecord);
//            if (record.getType() == SolutionRunTypeEnum.DEBUG) {
//                Solution updateSolution = new Solution();
//                updateSolution.setId(record.getSolutionId());
//                switch (status) {
//                    case Succeeded:
//                        updateSolution.setStatus(SolutionStatusEnum.READY);
//                        break;
//                    case Failed:
//                        updateSolution.setStatus(SolutionStatusEnum.ERROR);
//                        break;
//                    default:
//                        log.error("unknown solutionRun status.[status={}]", record.getStatus());
//                }
//                solutionService.updateById(updateSolution);
//            }
//        });
//        TenantContext.destroy();
    }

    @SneakyThrows
    public ModelPackage parseModelPackage(SolutionRun solutionRun) {
        Solution solution = solutionService.getById(solutionRun.getSolutionId());
        ModelPackage modelPackage = new ModelPackage();
        modelPackage.setSolutionId(solutionRun.getSolutionId());
        modelPackage.setSolutionRunId(solutionRun.getId());
        modelPackage.setName(solutionRun.getPRunName());
        modelPackage.setSolutionTitle(solution.getTitle());
        modelPackage.setLifecycle(modelManagementProperties.getDefaultLifecycle());
        String path = StrUtil.format("{}/{}", hdfsProperties.getModelBasePath(), solutionRun.getId().toString());
        modelPackage.setPath(path);
        modelPackage.setSystem(solution.getSystem());
        modelPackageService.save(modelPackage);
        List<FileStatus> fileStatuses = hdfsComponent.listFile(path);
        for (FileStatus fileStatus : fileStatuses) {
            if (!fileStatus.getPath().getName().endsWith(".json")) {
                continue;
            }
            Model model = new Model();
            String fileData = hdfsComponent.getFileFullDataStr(fileStatus.getPath().toUri().getPath());
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            ModelDesc modelDesc = objectMapper.readValue(fileData, ModelDesc.class);
            model.setSolutionId(modelPackage.getSolutionId());
            model.setResourceType(modelDesc.getType().equals("pk") ? ResourceTypeEnum.MODEL :
                    ResourceTypeEnum.DATA);
            model.setPackageId(modelPackage.getId());
            model.setName(modelDesc.getModelName());
            model.setMetricsList(modelDesc.getMetrics());
            model.setSize(hdfsComponent.getFileSizeMB(modelDesc.getModelHdfsPath()));
            modelService.save(model);
        }
        return modelPackage;
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
