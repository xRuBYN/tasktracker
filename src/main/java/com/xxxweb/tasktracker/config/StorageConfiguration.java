package com.xxxweb.tasktracker.config;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {

    private final ApplicationProperties properties;

    public StorageConfiguration(ApplicationProperties properties) {
        this.properties = properties;
    }
    //    @Bean
    //    public MinioClient minioClient() {
    //        ApplicationProperties.Minio minio = properties.minio();
    //        return MinioClient.builder()
    //            .endpoint(minio.uri())
    //            .credentials(minio.accessKey(), minio.secretKey())
    //            .build();
    //    }

}
