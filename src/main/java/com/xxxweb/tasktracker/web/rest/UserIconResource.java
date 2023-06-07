package com.xxxweb.tasktracker.web.rest;

import com.xxxweb.tasktracker.service.UserIconService;
import com.xxxweb.tasktracker.service.dto.UserIconDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/icon")
public class UserIconResource {

    private final UserIconService userIconService;

    public UserIconResource(UserIconService userIconService) {
        this.userIconService = userIconService;
    }

    @PostMapping
    public ResponseEntity<Void> addProfileIcon(@RequestBody UserIconDto dto) {
        userIconService.saveIcon(dto);
        return ResponseEntity.status(201).build();
    }
}
