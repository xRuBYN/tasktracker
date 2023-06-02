package com.xxxweb.tasktracker.service.dto;

import com.xxxweb.tasktracker.domain.enumeration.PriorityType;
import javax.validation.constraints.NotNull;

public class IssueRequestDto {

    @NotNull
    private String name;

    @NotNull
    private PriorityType priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }
}
