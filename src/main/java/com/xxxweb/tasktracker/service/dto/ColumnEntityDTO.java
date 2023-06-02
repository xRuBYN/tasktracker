package com.xxxweb.tasktracker.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link com.xxxweb.tasktracker.domain.ColumnEntity} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ColumnEntityDTO implements Serializable {

    private UUID id;

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private Instant createdDate;

    @NotNull
    @Size(max = 50)
    private String createdBy;

    @Size(max = 50)
    private String lastModifiedBy;

    private Instant lastModifiedDate;

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

    //    public ProjectDTO getProject() {
    //        return project;
    //    }
    //
    //    public void setProject(ProjectDTO project) {
    //        this.project = project;
    //    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ColumnEntityDTO)) {
            return false;
        }

        ColumnEntityDTO columnEntityDTO = (ColumnEntityDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, columnEntityDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ColumnEntityDTO{" +
            "id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
//            ", project=" + getProject() +
            "}";
    }
}