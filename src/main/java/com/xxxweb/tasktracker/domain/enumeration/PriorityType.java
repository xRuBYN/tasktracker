package com.xxxweb.tasktracker.domain.enumeration;

public enum PriorityType {
    LOWEST("Lowest"),
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High"),

    HIGHEST("Highest");

    private final String value;

    PriorityType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
