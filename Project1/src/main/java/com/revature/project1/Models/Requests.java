package com.revature.project1.Models;

public class Requests {

    private int requestID;
    private String requestStatus;
    private int requestAmount;
    private String requestType;
    private String requestRequester;

    public Requests() {}

    public Requests(int requestID, String requestStatus, int requestAmount, String requestType, String requestRequester) {
        this.requestID = requestID;
        this.requestStatus = requestStatus;
        this.requestAmount = requestAmount;
        this.requestType = requestType;
        this.requestRequester = requestRequester;
    }



    public int getRequestID() { return requestID; }

    public void setRequestID(int requestID) { this.requestID = requestID;}

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public int getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(int requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getRequestType() {return requestType; }

    public void setRequestType(String requestType) { this.requestType = requestType; }

    public String getRequestRequester() { return requestRequester; }

    public void setRequestRequester(String requestRequester) { this.requestRequester = requestRequester; }
}
