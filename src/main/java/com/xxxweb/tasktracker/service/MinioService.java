package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.config.ApplicationProperties;
import io.minio.CopyObjectArgs;
import io.minio.CopySource;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import java.io.ByteArrayInputStream;
import java.io.File;
import org.springframework.stereotype.Service;
//@Service
//public class MinioService implements StorageService {
//
//    private final MinioClient minioClient;
//    private String rootBucket;
//
//    public MinioService(MinioClient minioClient, ApplicationProperties properties) {
//        this.minioClient = minioClient;
//        this.rootBucket = properties.minio().bucketName();
//    }
//
//    public String uploadToStorage(String fileName, byte[] data) {
//        try {
//            minioClient.putObject(
//                PutObjectArgs.builder().bucket(rootBucket).object(fileName).stream(new ByteArrayInputStream(data), data.length, -1).build()
//            );
//            return rootBucket + File.separator + fileName;
//        } catch (Exception e) {
//            throw new RuntimeException("Put object minio error : " + e.getMessage());
//        }
//    }
//
//    public void removeObject(String objectName) {
//        try {
//            minioClient.removeObject(RemoveObjectArgs.builder().bucket(rootBucket).object(objectName).build());
//        } catch (Exception e) {
//            throw new RuntimeException("Remove object from minio error : " + e.getMessage());
//        }
//    }
//
//    public void copyObject(String objectName, String destPath) {
//        try {
//            CopyObjectArgs copyObjectArgs = CopyObjectArgs
//                .builder()
//                .bucket(rootBucket)
//                .object(destPath)
//                .source(CopySource.builder().bucket(rootBucket).object(objectName).build())
//                .build();
//            ObjectWriteResponse response = minioClient.copyObject(copyObjectArgs);
//        } catch (Exception e) {
//            throw new RuntimeException("Copy object from minio error : " + e.getMessage());
//        }
//    }
//
//    public byte[] getObjectByPath(String object) {
//        try {
//            GetObjectResponse response = minioClient.getObject(GetObjectArgs.builder().bucket(rootBucket).object(object).build());
//            return response.readAllBytes();
//        } catch (Exception e) {
//            throw new RuntimeException("Get object from minio error : " + e.getMessage());
//        }
//    }
//}
