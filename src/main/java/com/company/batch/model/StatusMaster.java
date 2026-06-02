package com.company.batch.model;

public class StatusMaster {
    private String statusCode;
    private String statusName;

    public StatusMaster() {
    }

    public StatusMaster(String statusCode, String statusName) {
        this.statusCode = statusCode;
        this.statusName = statusName;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "StatusMaster{statusCode='" + statusCode + "', statusName='" + statusName + "'}";
    }
}
