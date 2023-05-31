package com.xxxweb.tasktracker.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IssueMapperTest {

    private IssueMapper issueMapper;

    @BeforeEach
    public void setUp() {
        issueMapper = new IssueMapperImpl();
    }
}
