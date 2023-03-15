package com.digitforce.aip.utils;


import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.Bucket;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * S3工具类
 * 参考：<a href="https://docs.aws.amazon.com/sdk-for-java/latest/developer-guide/get-started.html"></a>
 *
 * @author wangtonggui
 */
@Slf4j
@UtilityClass
public class S3Helper {
    private final S3Client client = S3Client.builder()
            .endpointOverride(URI.create("http://172.24.20.91:32222/"))
            .region(Region.US_EAST_1)
            .credentialsProvider(
                    StaticCredentialsProvider.create(AwsBasicCredentials.create("FzaylFbX7h6tte12",
                            "l2hOAWMweie7UP4MZpxB8icNdGezodel")))
            .serviceConfiguration(S3Configuration.builder().build())
            .forcePathStyle(true)
            .build();

    public void deleteObject(String bucketName, String key) {
        client.deleteObject(builder -> builder.bucket(bucketName).key(key));
    }

    public void deletePrefix(String bucketName, String prefix) {
        client.listObjectsV2Paginator(builder -> builder.bucket(bucketName).prefix(prefix))
                .contents()
                .forEach(object -> deleteObject(bucketName, object.key()));
    }

    public List<String> listBuckets() {
        return client.listBuckets().buckets().stream().map(Bucket::name).collect(Collectors.toList());
    }
}
