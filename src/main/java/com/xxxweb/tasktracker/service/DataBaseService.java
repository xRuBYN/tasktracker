package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.User;
import com.xxxweb.tasktracker.domain.UserIcon;
import com.xxxweb.tasktracker.repository.UserIconRepository;
import org.springframework.stereotype.Service;

@Service
public class DataBaseService implements StorageService {

    private final UserIconRepository userIconRepository;

    private final UserService userService;

    public DataBaseService(UserIconRepository userIconRepository, UserService userService) {
        this.userIconRepository = userIconRepository;
        this.userService = userService;
    }

    @Override
    public String uploadToStorage(String fileName, byte[] data) {
        User user = userService.getCurrentUserByLogin();
        UserIcon userIcon = new UserIcon();
        userIcon.setName(fileName);
        userIcon.setData(data);
        UserIcon savedIcon = userIconRepository.save(userIcon);
        user.setIcon(savedIcon);
        userService.update(user);
        return savedIcon.getName();
    }

    @Override
    public void removeObject(String objectName) {
        throw new RuntimeException("Method is in develop");
    }

    @Override
    public void copyObject(String objectName, String destPath) {
        throw new RuntimeException("Method is in develop");
    }

    @Override
    public byte[] getObjectByPath(String path) {
        throw new RuntimeException("Method is in develop");
    }
}
