package com.massimo.web.app.config;

public enum TypeMessage {
    ERROR("error"),
    WARNING("warning"),
    SUCCESSFUL("success");

    private String name;

    TypeMessage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

