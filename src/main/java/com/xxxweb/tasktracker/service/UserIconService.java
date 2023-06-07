package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.service.dto.UserIconDto;
import org.springframework.stereotype.Service;

@Service
public class UserIconService {

    private final DataBaseService dataBaseService;

    public UserIconService(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void saveIcon(UserIconDto dto) {
        dataBaseService.uploadToStorage(dto.getName(), dto.getData());
    }
}
