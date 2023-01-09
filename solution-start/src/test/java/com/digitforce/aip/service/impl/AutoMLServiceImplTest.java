package com.digitforce.aip.service.impl;

import com.digitforce.aip.entity.dto.data.BestParameter;
import com.digitforce.aip.enums.AutoMLRunStatusEnum;
import com.digitforce.aip.service.AutoMLService;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import java.util.List;

import javax.annotation.Resource;

/**
 * 自动调参服务接口类测试类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2023/01/09 23:00
 */
public class AutoMLServiceImplTest extends BaseTest {
    @Resource
    private AutoMLService autoMLService;

    @Test
    public void createTask() {
    }

    @Test
    public void getStatus() {
        AutoMLRunStatusEnum status = autoMLService.getStatus("315ec4eb-8b91-4b77-8e4e-73a00fd6a2d3");
        System.out.println(status);
    }

    @Test
    public void getAutoMLResult() {
        List<BestParameter> bestParameters = autoMLService.getAutoMLResult("315ec4eb-8b91-4b77-8e4e-73a00fd6a2d3");
        System.out.println(bestParameters);
    }
}