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
        triggerRunCmd.setName("run_name_1231");
        triggerRunCmd.setExperimentId(kubeflowProperties.getExperimentId());
        triggerRunCmd.setPipelineId("4f8b48ec-ec2d-4d7c-8a0e-ccee8f5cfc29");
        triggerRunCmd.setTimeRange(1000);
        triggerRunCmd.setTimeUnit(ChronoUnit.DAYS);
        String runId = KubeflowHelper.triggerRun(kubeflowProperties.getHost(), kubeflowProperties.getPort(),
                123, triggerRunCmd);
        System.out.println(runId);
    }
}