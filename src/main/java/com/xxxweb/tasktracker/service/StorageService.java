package com.xxxweb.tasktracker.service;

public interface StorageService {
    String uploadToStorage(String fileName, byte[] data);

    void removeObject(String objectName);

    void copyObject(String objectName, String destPath);

    byte[] getObjectByPath(String path);
}
