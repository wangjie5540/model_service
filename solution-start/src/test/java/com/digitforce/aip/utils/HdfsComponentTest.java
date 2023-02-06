package com.digitforce.aip.utils;

import cn.hutool.core.util.StrUtil;
import com.digitforce.aip.config.HdfsProperties;
import com.digitforce.aip.service.component.HdfsComponent;
import com.digitforce.aip.test.BaseTest;
import org.apache.hadoop.fs.FileStatus;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

public class HdfsComponentTest extends BaseTest {
    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private HdfsComponent hdfsComponent;
    @Resource
    private HdfsProperties hdfsProperties;


    @Test
    public void listFile() throws IOException {
        List<FileStatus> fileStatuses = hdfsComponent.listFile(StrUtil.format("{}/{}",
                hdfsProperties.getModelBasePath(), "1612027828000432130"));
        System.out.println(fileStatuses);
    }

    @Test
    public void getActiveProfile() {
        String profile = applicationContext.getEnvironment().getActiveProfiles()[0];
        Assert.assertEquals("dev", profile);
    }
}