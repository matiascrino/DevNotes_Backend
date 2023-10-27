package com.example.backend_java.dtos;

import java.io.Serial;
import java.io.Serializable;

public class PostCreationDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String title;
    private String content;
    private long exposureId;

    private int expiresAt;

    private String userEmail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getExposureId() {
        return exposureId;
    }

    public void setExposureId(long exposureId) {
        this.exposureId = exposureId;
    }

    public int getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(int expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
