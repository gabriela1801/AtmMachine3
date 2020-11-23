package com.example.AtmMachine.config;

public class Response {
    public static final String SUCCESS = "SUCCESS";
    public static final String FAILURE = "FAILURE";
    private String responseStatus;
    private String description;
    private Integer value;

    public Response(
            String responseStatus,
            String description
    ) {
        this.setResponseStatus(responseStatus);
        this.setDescription(description);
    }

    public Response(
            String responseStatus,
            String description,
            Integer value
    ) {
        this.responseStatus = responseStatus;
        this.description = description;
        this.value = value;
    }

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}