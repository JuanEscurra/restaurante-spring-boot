package com.massimo.web.app.config;

public class Message {

    private String value;
    private TypeMessage type;

    public Message() {
    }
    public Message(String value, TypeMessage type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public TypeMessage getType() {
        return type;
    }

    public void setType(TypeMessage type) {
        this.type = type;
    }
}