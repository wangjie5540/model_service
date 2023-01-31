package com.digitforce.aip;

import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.model.TriggerRunCmd;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.temporal.ChronoUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class KubeflowHelperTest {
    @Resource
    private KubeflowProperties kubeflowProperties;

    @Test
    public void pageByPipeline() {
        System.out.println(KubeflowHelper.pageByPipeline(kubeflowProperties.getHost(), kubeflowProperties.getPort(),
                20));
    }

    @Test
    public void stopTest() {
        KubeflowHelper.stopRun(kubeflowProperties.getHost(), kubeflowProperties.getPort(),
                "faf1f39f-7e02-4e4a-bbfa-ed34d3811181");
    }

    @Test
    public void triggerTest() {
        TriggerRunCmd triggerRunCmd = new TriggerRunCmd();
        triggerRunCmd.setName("test_run_wtg");
        triggerRunCmd.setExperimentId(kubeflowProperties.getExperimentId());
        triggerRunCmd.setPipelineId("14f84a2a-12fe-40e3-b192-306f2aa83930");
        triggerRunCmd.setSolutionId(95L);
        triggerRunCmd.setTimeRange(1000);
        triggerRunCmd.setTimeUnit(ChronoUnit.DAYS);
        String runId = KubeflowHelper.triggerRun(kubeflowProperties.getHost(), kubeflowProperties.getPort(),
                123, triggerRunCmd);
        System.out.println(runId);
    }

    @Test
    public void createRun() {
        String train = KubeflowHelper.createRun(
                kubeflowProperties.getHost(),
                kubeflowProperties.getPort(),
                kubeflowProperties.getExperimentId(),
                "6f6da3aa-423c-4104-9d30-6832af2aca2a",
                "wtg-test-run1",
                "{}",
                "TRAIN"
        );
        System.out.println(train);
    }
}