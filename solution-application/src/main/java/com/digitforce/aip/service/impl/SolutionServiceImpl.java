package com.digitforce.aip.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.aip.dto.cmd.SolutionUnPublishCmd;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.mapper.SolutionMapper;
import com.digitforce.aip.quartz.SolutionQuartzJob;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.tool.ConvertTool;
import lombok.SneakyThrows;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Service;

import java.util.Objects;

import javax.annotation.Resource;

/**
 * <p>
 * 方案表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionMapper, Solution> implements ISolutionService {
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private Scheduler scheduler;

    @Override
    public void createAndRun(SolutionAddCmd solutionAddCmd) {
        Solution solution = ConvertTool.convert(solutionAddCmd, Solution.class);
        super.save(solution);
        solutionRunService.createRun(solution, solutionAddCmd.getPipelineParams(), SolutionRunTypeEnum.DEBUG);
    }

    @Override
    public void publish(SolutionPublishCmd solutionPublishCmd) {
        Solution solution = ConvertTool.convert(solutionPublishCmd, Solution.class);
        Solution savedSolution = getById(solution.getId());
        if (Objects.isNull(savedSolution)) {
            throw new RuntimeException("方案不存在");
        }
        switch (savedSolution.getStatus()) {
            case PUBLISHED:
                throw new RuntimeException("方案已发布");
            case EXECUTING:
                throw new RuntimeException("方案正在执行");
            case ERROR:
                throw new RuntimeException("方案执行失败");
            case READY:
                solution.setStatus(SolutionStatusEnum.PUBLISHED);
                updateById(solution);
                scheduleJob(solutionPublishCmd);
                break;
            default:
                throw new RuntimeException("方案状态异常");
        }
    }

    @SneakyThrows
    private void scheduleJob(SolutionPublishCmd solutionPublishCmd) {
        Integer tenantId = TenantContext.tenant().getTenantId();
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("solutionId", solutionPublishCmd.getId());
        JobDetail jobDetail = JobBuilder.newJob(SolutionQuartzJob.class)
            .withIdentity(solutionPublishCmd.getId().toString(), tenantId.toString())
            .setJobData(jobDataMap)
            .build();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(solutionPublishCmd.getCron());
        CronTrigger cronTrigger =
            TriggerBuilder.newTrigger().withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    @SneakyThrows
    @Override
    public void unPublish(SolutionUnPublishCmd solutionUnPublishCmd) {
        Solution solution = ConvertTool.convert(solutionUnPublishCmd, Solution.class);
        solution.setStatus(SolutionStatusEnum.READY);
        updateById(solution);
        scheduler.deleteJob(
            JobKey.jobKey(solutionUnPublishCmd.getId().toString(), TenantContext.tenant().getTenantId().toString()));
    }
}
