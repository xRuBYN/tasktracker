package com.xxxweb.tasktracker.service.dto;

import com.xxxweb.tasktracker.domain.enumeration.PriorityType;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.xxxweb.tasktracker.domain.Issue} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IssueDTO implements Serializable {

    private UUID id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Size(max = 255)
    private String description;

    private PriorityType priority;

    @NotNull
    private Instant createdDate;

    @NotNull
    @Size(max = 50)
    private String createdBy;

    @Size(max = 50)
    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private ColumnEntityDTO column;

    private UserDTO assigned;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public ColumnEntityDTO getColumn() {
        return column;
    }

    public void setColumn(ColumnEntityDTO column) {
        this.column = column;
    }

    public UserDTO getAssigned() {
        return assigned;
    }

    public void setAssigned(UserDTO assigned) {
        this.assigned = assigned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof IssueDTO)) {
            return false;
        }

        IssueDTO issueDTO = (IssueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, issueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IssueDTO{" +
            "id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", priority='" + getPriority() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            ", column=" + getColumn() +
            ", assigned=" + getAssigned() +
            "}";
    }
}
