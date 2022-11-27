package com.revature.project1.Util.DTO;
import com.fasterxml.jackson.annotation.JsonAlias;
public class RequestSubmit {

    @JsonAlias(value = {"r_id",})
    private int requestID;
    @JsonAlias(value = {"r_status",})
    private String requestStatus;

    public RequestSubmit() {}

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

}