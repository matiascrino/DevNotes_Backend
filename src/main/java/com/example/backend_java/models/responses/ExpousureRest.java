package com.example.backend_java.models.responses;

public class ExpousureRest {

    private long expousureId;

    private String type;



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getExpousureId() {
        return expousureId;
    }

    public void setExpousureId(long expousureId) {
        this.expousureId = expousureId;
    }
}
