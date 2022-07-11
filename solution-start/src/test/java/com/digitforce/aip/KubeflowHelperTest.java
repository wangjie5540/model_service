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
        System.out.println(KubeflowHelper.pageByPipeline(kubeflowProperties.getHost(), kubeflowProperties.getPort(), 20));
    }
}