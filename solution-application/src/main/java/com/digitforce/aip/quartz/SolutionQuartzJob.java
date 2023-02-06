package com.digitforce.aip.quartz;

import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.framework.context.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * quartz调用
 *
 * @author wangtonggui
 */
@Slf4j
@Component
public class SolutionQuartzJob extends QuartzJobBean {
    private static final ThreadLocal<Date> threadLocal = new ThreadLocal<>();
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private ISolutionService solutionService;

    @Override
    protected void executeInternal(@NotNull JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Integer tenantId = (Integer) jobExecutionContext.getJobDetail().getJobDataMap().get("tenantId");
        TenantContext.init(tenantId);
        Long solutionId = (Long) jobExecutionContext.getJobDetail().getJobDataMap().get("solutionId");
        Solution solution = solutionService.getById(solutionId);
        solutionRunService.createRun(solution, SolutionRunTypeEnum.DEPLOY, solution.getTemplateParams());
    }
}
