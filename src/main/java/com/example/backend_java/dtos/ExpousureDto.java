package com.example.backend_java.dtos;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ExpousureDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

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
