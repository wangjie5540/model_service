package com.digitforce.aip;

import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.utils.KubeflowHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

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
    public void createRun() {
        String train = KubeflowHelper.createRun(
                kubeflowProperties.getHost(),
                kubeflowProperties.getPort(),
                kubeflowProperties.getExperimentId(),
                "e0e53e2b-4f71-42f1-a675-5eb1f39a1852",
                "wtg-test-run1",
                "{}",
                "TRAIN"
        );
        System.out.println(train);
    }
}