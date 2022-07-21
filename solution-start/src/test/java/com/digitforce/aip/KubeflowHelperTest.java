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
        KubeflowHelper.stopRun(kubeflowProperties.getHost(), kubeflowProperties.getPort(), "d400dc88-3d07-42aa-9b71" +
                "-3012414b421e");
    }

    @Test
    public void triggerTest() {
        TriggerRunCmd triggerRunCmd = new TriggerRunCmd();
        triggerRunCmd.setName("run_name_1231");
        triggerRunCmd.setExperimentId(GlobalConstant.DEFAULT_EXPERIMENT_ID);
        triggerRunCmd.setPipelineId("3c094336-21aa-4b54-8833-2bf10e7d2085");
        triggerRunCmd.setTimeRange(1000);
        triggerRunCmd.setTimeUnit(ChronoUnit.DAYS);
        String runId = KubeflowHelper.triggerRun(kubeflowProperties.getHost(), kubeflowProperties.getPort(),
                123, triggerRunCmd);
        System.out.println(runId);
    }
}