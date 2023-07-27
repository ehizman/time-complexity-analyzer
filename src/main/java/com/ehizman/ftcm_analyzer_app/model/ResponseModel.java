package com.ehizman.ftcm_analyzer_app.model;

public class ResponseModel {
    public ResponseModel(String response, boolean isSuccessful) {
        this.response = response;
        this.isSuccessful = isSuccessful;
    }

    private String response;
    private boolean isSuccessful;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }
}
