package com.datagrokr.entity;

public class HelperEntity {
    
    private int status;
    private String message;

    public HelperEntity() {
    
    }

    public HelperEntity(int status, String message) {
        this.status = status;
        this.message = message;
    }
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "HelperEntity [message=" + message + ", status=" + status + "]";
    }

    
}
