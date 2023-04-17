package com.digitforce.aip.utils;


import com.digitforce.aip.config.S3Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.Bucket;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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
@Component
public class S3Helper {
    private S3Client client;
    @Resource
    private S3Properties s3Properties;

    @PostConstruct
    public void postConstruct() {
        client = S3Client.builder()
                .endpointOverride(URI.create(s3Properties.getEndpoint()))
                .region(Region.of(s3Properties.getRegion()))
                .credentialsProvider(
                        StaticCredentialsProvider.create(AwsBasicCredentials.create(s3Properties.getAccessKey(),
                                s3Properties.getSecretKey())))
                .serviceConfiguration(S3Configuration.builder().build())
                .forcePathStyle(true)
                .build();
    }


    public String getObjectContentAsString(String key) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(s3Properties.getBucket())
                .key(key)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes = client.getObject(getObjectRequest,
                ResponseTransformer.toBytes());

        return objectBytes.asUtf8String();
    }

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
