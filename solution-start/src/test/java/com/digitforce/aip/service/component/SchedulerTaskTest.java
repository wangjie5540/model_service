package com.digitforce.aip.service.component;

import com.digitforce.aip.test.BaseTest;
import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;


public class SchedulerTaskTest extends BaseTest {
    @Resource
    private SchedulerTask schedulerTask;
    @Resource
    private Scheduler scheduler;
    @Resource
    private ApplicationContext applicationContext;


    @Test
    public void getAllBeans() {
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

//    @Test
//    public void getScheduler() throws SchedulerException {
//        for (Scheduler s : schedulerFactory.getAllSchedulers()) {
//            System.out.println(s.getSchedulerName());
//        }
//    }

    @Test
    public void getSchedulerName() throws SchedulerException, InterruptedException {
        System.out.println(scheduler.getSchedulerName());
        System.out.println(scheduler.isStarted());
        scheduler.getJobGroupNames().forEach(System.out::println);
        scheduler.start();
        TimeUnit.SECONDS.sleep(50);
        System.out.println(scheduler.isStarted());
    }
}