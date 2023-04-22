package com.digitforce.aip.config;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.quartz.jobs.PatrolSolutionRunStatusJob;
import org.quartz.Calendar;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;

/**
 * 定时任务配置（单机部署建议删除此类和qrtz数据库表，默认走内存会最高效）
 *
 * @author ruoyi
 */
@Configuration
public class QuartzScheduleConfig {
    @Bean
    public SchedulerFactoryBean quartzScheduler(QuartzProperties properties, DataSource dataSource,
                                                ObjectProvider<SchedulerFactoryBeanCustomizer> customizers,
                                                ObjectProvider<JobDetail> jobDetails, Map<String, Calendar> calendars
            , ObjectProvider<Trigger> triggers, ApplicationContext applicationContext) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        schedulerFactoryBean.setJobFactory(jobFactory);
        if (properties.getSchedulerName() != null) {
            schedulerFactoryBean.setSchedulerName(properties.getSchedulerName());
        }
        schedulerFactoryBean.setAutoStartup(properties.isAutoStartup());
        schedulerFactoryBean.setStartupDelay((int) properties.getStartupDelay().getSeconds());
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(properties.isWaitForJobsToCompleteOnShutdown());
        schedulerFactoryBean.setOverwriteExistingJobs(properties.isOverwriteExistingJobs());
        if (!properties.getProperties().isEmpty()) {
            schedulerFactoryBean.setQuartzProperties(asProperties(properties.getProperties()));
        }
        schedulerFactoryBean.setJobDetails(jobDetails.orderedStream().toArray(JobDetail[]::new));
        schedulerFactoryBean.setCalendars(calendars);
        schedulerFactoryBean.setTriggers(triggers.orderedStream().toArray(Trigger[]::new));
        customizers.orderedStream().forEach((customizer) -> customizer.customize(schedulerFactoryBean));
        schedulerFactoryBean.setDataSource(dataSource);
        return schedulerFactoryBean;
    }

    private Properties asProperties(Map<String, String> source) {
        Properties properties = new Properties();
        properties.putAll(source);
        return properties;
    }

    @Bean
    public JobDetail patrolSolutionRunStatusJob() {
        return JobBuilder.newJob(PatrolSolutionRunStatusJob.class)
                .withIdentity("patrolSolutionRunStatusJob", CommonConst.CUSTOM_JOB_GROUP)
                .withDescription("巡检方案运行状态定时任务")
                .storeDurably()
                .build();
    }


    @Bean
    public Trigger patrolSolutionRunStatusJobTrigger(JobDetail patrolSolutionRunStatusJob) {
        return TriggerBuilder.newTrigger()
                .forJob(patrolSolutionRunStatusJob)
                .withIdentity("patrolSolutionRunStatusJobTrigger", CommonConst.CUSTOM_JOB_GROUP)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(20)
                        .repeatForever())
                .build();
    }
}