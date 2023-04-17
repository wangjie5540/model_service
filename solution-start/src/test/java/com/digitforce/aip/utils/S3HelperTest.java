package com.digitforce.aip.utils;

import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class S3HelperTest extends BaseTest {
    @Resource
    public S3Helper s3Helper;

    @Test
    public void deletePrefix() {
        s3Helper.deletePrefix("warehouse", "test_model_id/");
    }

    @Test
    public void listBuckets() {
        System.out.println(s3Helper.listBuckets());
    }

    @Test
    public void getObject() {
        String str = s3Helper.getObjectContentAsString("test_s3_helper.py");
        System.out.println(str);
    }
}