package com.digitforce.aip;

import com.digitforce.aip.config.KubeflowProperties;
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
        KubeflowHelper.stopRun(kubeflowProperties.getHost(), kubeflowProperties.getPort(), "d400dc88-3d07-42aa-9b71" +
                "-3012414b421e");
    }
}