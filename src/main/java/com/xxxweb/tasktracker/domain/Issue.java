package com.xxxweb.tasktracker.domain;

import com.xxxweb.tasktracker.domain.enumeration.PriorityType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "issue")
public class Issue extends AbstractAuditingEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @javax.persistence.Column(name = "name")
    private String name;

    @javax.persistence.Column(name = "description")
    private String description;

    @ManyToOne
    private Column column;

    @ManyToOne
    private User assigned;

    @Enumerated(EnumType.STRING)
    @javax.persistence.Column(name = "priority_type", nullable = false)
    private PriorityType type;

    public PriorityType getType() {
        return type;
    }

    public void setType(PriorityType type) {
        this.type = type;
    }

    public User getAssigned() {
        return assigned;
    }

    public void setAssigned(User assigned) {
        this.assigned = assigned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Issue issue = (Issue) o;
        return (
            Objects.equals(id, issue.id) &&
            Objects.equals(name, issue.name) &&
            Objects.equals(description, issue.description) &&
            Objects.equals(column, issue.column)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, column);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }
}
