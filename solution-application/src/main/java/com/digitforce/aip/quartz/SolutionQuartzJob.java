package com.digitforce.aip.quartz;

import com.digitforce.aip.service.ISolutionRunService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

import javax.annotation.Resource;

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

    @Override
    protected void executeInternal(@NotNull JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Long solutionId = (Long) jobExecutionContext.getJobDetail().getJobDataMap().get("solutionId");
//        solutionRunService.createRun(
//            11L,
//            "111",
//            "111",
//            SolutionRunTypeEnum.DEPLOY
//        );
    }
}
