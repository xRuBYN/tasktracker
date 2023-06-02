package com.xxxweb.tasktracker.service.dto;

import java.util.UUID;

public class MoveIssueDto {

    private UUID newColumnId;

    private UUID issueId;

    public UUID getNewColumnId() {
        return newColumnId;
    }

    public void setNewColumnId(UUID newColumnId) {
        this.newColumnId = newColumnId;
    }

    public UUID getIssueId() {
        return issueId;
    }

    public void setIssueId(UUID issueId) {
        this.issueId = issueId;
    }
}
