package com.xxxweb.tasktracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.xxxweb.tasktracker.domain.enumeration.PriorityType;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A Issue.
 */
@Entity
@Table(name = "issue")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Issue extends AbstractAuditingEntity<UUID> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private PriorityType priority;

    @ManyToOne
    @JsonIgnoreProperties(value = { "project" }, allowSetters = true)
    private ColumnEntity column;

    @ManyToOne
    private User assigned;

    public static IssueBuilder builder() {
        return new IssueBuilder();
    }

    public static class IssueBuilder {

        private Issue issue = new Issue();

        public IssueBuilder name(String name) {
            issue.setName(name);
            return this;
        }

        public IssueBuilder description(String description) {
            issue.setDescription(description);
            return this;
        }

        public IssueBuilder priority(PriorityType priority) {
            issue.setPriority(priority);
            return this;
        }

        public IssueBuilder column(ColumnEntity column) {
            issue.setColumn(column);
            return this;
        }

        public IssueBuilder assigned(User assigned) {
            issue.setAssigned(assigned);
            return this;
        }

        public Issue build() {
            return issue;
        }
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public UUID getId() {
        return this.id;
    }

    public Issue id(UUID id) {
        this.setId(id);
        return this;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public Issue name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public Issue description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityType getPriority() {
        return this.priority;
    }

    public Issue priority(PriorityType priority) {
        this.setPriority(priority);
        return this;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public ColumnEntity getColumn() {
        return this.column;
    }

    public void setColumn(ColumnEntity columnEntity) {
        this.column = columnEntity;
    }

    public Issue column(ColumnEntity columnEntity) {
        this.setColumn(columnEntity);
        return this;
    }

    public User getAssigned() {
        return this.assigned;
    }

    public void setAssigned(User user) {
        this.assigned = user;
    }

    public Issue assigned(User user) {
        this.setAssigned(user);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Issue)) {
            return false;
        }
        return id != null && id.equals(((Issue) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Issue{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", priority='" + getPriority() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", lastModifiedBy='" + getLastModifiedBy() + "'" +
            ", lastModifiedDate='" + getLastModifiedDate() + "'" +
            "}";
    }
}
