package com.example.backend_java.entities;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name="expousures")
public class ExpousureEntity implements Serializable {

    @Serial
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue
    private long expousureId;
    @Column(nullable = false, length = 50)
    private String type;


    public long getExpousureId() {
        return expousureId;
    }

    public void setExpousureId(long expousureId) {
        this.expousureId = expousureId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
