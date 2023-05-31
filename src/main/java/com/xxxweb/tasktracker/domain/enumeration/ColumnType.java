package com.xxxweb.tasktracker.domain.enumeration;

public enum ColumnType {
    TODO("ToDo"),
    DONE("Done");

    private final String value;

    ColumnType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
