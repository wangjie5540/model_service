package com.digitforce.aip.utils;

import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

public class S3HelperTest extends BaseTest {
    @Test
    public void deletePrefix() {
        S3Helper.deletePrefix("warehouse", "test_model_id/");
    }

    @Test
    public void listBuckets() {
        System.out.println(S3Helper.listBuckets());
    }
}