package com.econcours.econcoursservice.logger.enumeration;

/**
 * @author Ibrahim Maiga <maiga.ibrm@gmail.com>
 */
public enum LogType {
    CREATE("create", "creation logs"),
    UPDATE("update", "update logs"),
    DELETE("delete", "delete logs");

    private String name;
    private String description;

    LogType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean isCreate() {
        return this == CREATE;
    }

    public boolean isUpdate() {
        return this == UPDATE;
    }

    public boolean isDelete() {
        return this == DELETE;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
