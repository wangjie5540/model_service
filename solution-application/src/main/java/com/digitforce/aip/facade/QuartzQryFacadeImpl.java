package com.digitforce.aip.facade;

import com.digitforce.framework.api.dto.Result;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 定时任务查询实现类
 */
@RestController
@Slf4j
public class QuartzQryFacadeImpl implements QuartzQryFacade {
    @Resource
    private Scheduler scheduler;

    @SneakyThrows
    @Override
    public Result<List<String>> getTriggerGroups() {
        log.info("调度器名称：{}", scheduler.getSchedulerName());
        log.info("调度器实例ID：{}", scheduler.getSchedulerInstanceId());
        log.info("调度器实例名称：{}", scheduler.getSchedulerName());
        log.info("调度器实例是否启动：{}", scheduler.isStarted());
        log.info("调度器实例是否关闭：{}", scheduler.isShutdown());
        log.info("调度器实例是否暂停：{}", scheduler.isInStandbyMode());
        log.info("源信息：{}", scheduler.getMetaData());
        for (String groupName : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                System.out.println("Job Name: " + jobDetail.getKey().getName() + ", Group Name: " + jobDetail.getKey().getGroup());
                System.out.println("Job Class: " + jobDetail.getJobClass());
                System.out.println("Job Description: " + jobDetail.getDescription());
            }
        }
        return Result.success(scheduler.getJobGroupNames());
    }

    @SneakyThrows
    @Override
    public Result<Object> triggerPageBy() {
        return null;
    }
}
