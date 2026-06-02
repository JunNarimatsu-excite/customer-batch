package com.example.model;

public class StatusMaster {
    private String code;
    private String label;

    public StatusMaster() {
    }

    public StatusMaster(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "StatusMaster{code='" + code + "', label='" + label + "'}";
    }
}
