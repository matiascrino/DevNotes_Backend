package com.example.backend_java.models.responses;

import com.example.backend_java.auth.models.responses.AuthRest;
import com.example.backend_java.dtos.ExpousureDto;
import com.example.backend_java.dtos.UserDto;

import java.util.Date;

public class PostRest {

    private String postId;

    private String title;

    private String content;

    private Date expiresAt;

    private Date createdAt;

    private boolean expired = false;

    private UserRest user;

    private ExpousureRest expousure;

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

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

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public ExpousureRest getExpousure() {
        return expousure;
    }

    public void setExpousure(ExpousureRest expousure) {
        this.expousure = expousure;
    }

    public UserRest getUser() {
        return user;
    }

    public void setUser(UserRest user) {
        this.user = user;
    }
}
