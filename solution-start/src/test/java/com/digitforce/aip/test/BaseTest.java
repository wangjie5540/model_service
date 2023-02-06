package com.digitforce.aip.test;

import com.digitforce.framework.context.TenantContext;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class BaseTest {
    @BeforeClass
    public static void before() {
        TenantContext.init(10000);
    }
}
