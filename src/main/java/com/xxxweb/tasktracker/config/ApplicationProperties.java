package com.xxxweb.tasktracker.config;

import javax.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Tasktracker.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public record ApplicationProperties(@NotEmpty String appFolder, @NotEmpty String mailFrom, Minio minio) {
    public record Minio(@NotEmpty String uri, @NotEmpty String accessKey, @NotEmpty String secretKey, @NotEmpty String bucketName) {}
}
