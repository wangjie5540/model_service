package com.digitforce.aip.quartz.jobs;

import cn.hutool.core.util.StrUtil;
import com.digitforce.aip.config.HdfsProperties;
import com.digitforce.aip.config.ModelManagementProperties;
import com.digitforce.aip.dto.data.ModelDesc;
import com.digitforce.aip.entity.Model;
import com.digitforce.aip.entity.ModelPackage;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.ResourceTypeEnum;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.mapper.SolutionRunMapper;
import com.digitforce.aip.service.IModelPackageService;
import com.digitforce.aip.service.IModelService;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.aip.service.component.HdfsComponent;
import com.digitforce.framework.context.TenantContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.fs.FileStatus;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Slf4j
public class PatrolSolutionRunStatusJob extends QuartzJobBean implements Serializable {
    private static final long serialVersionUID = -1482286901769302351L;
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private SolutionRunMapper solutionRunMapper;
    @Resource
    private KubeflowPipelineService kubeflowPipelineService;
    @Resource
    private IModelPackageService modelPackageService;
    @Resource
    private ISolutionService solutionService;
    @Resource
    private ModelManagementProperties modelManagementProperties;
    @Resource
    private HdfsProperties hdfsProperties;
    @Resource
    private HdfsComponent hdfsComponent;
    @Resource
    private IModelService modelService;

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("PatrolSolutionRunStatusJob");
        List<SolutionRun> solutionRunList = solutionRunMapper.getSomeRunningRecordsWithoutTenant(20);
        solutionRunList.forEach(record -> {
            TenantContext.init(record.getTenantId());
            RunStatusEnum status = kubeflowPipelineService.getStatus(record.getPRunId());
            SolutionRun updateRecord = new SolutionRun();
            switch (status) {
                case Running:
                    return;
                case Succeeded:
                    try {
                        ModelPackage modelPackage = parseModelPackage(record);
                        updateRecord.setPackageId(modelPackage.getId());
                    } catch (Exception e) {
                        log.error("解析modelPackage失败", e);
                        status = RunStatusEnum.Failed;
                    }
                    break;
                case Error:
                case Failed:
                    break;
                default:
                    log.error("未知的solution-run状态: {}", status);
            }
            updateRecord.setId(record.getId());
            updateRecord.setStatus(status);
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
}