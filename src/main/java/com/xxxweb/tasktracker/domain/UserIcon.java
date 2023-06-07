package com.xxxweb.tasktracker.domain;

import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "user_icon")
public class UserIcon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

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

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserIcon userIcon = (UserIcon) o;
        return (
            Objects.equals(id, userIcon.id) &&
            Objects.equals(name, userIcon.name) &&
            Objects.equals(type, userIcon.type) &&
            Arrays.equals(data, userIcon.data)
        );
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, type);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
