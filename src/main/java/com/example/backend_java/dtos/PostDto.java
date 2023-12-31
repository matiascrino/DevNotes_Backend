package com.example.backend_java.dtos;

import com.example.backend_java.models.responses.ExpousureRest;
import com.example.backend_java.models.responses.UserRest;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class PostDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private long id;

    private String postId;

    private String title;

    private String content;

    private Date expiresAt;

    private Date createdAt;

    private Date updatedAt;

    private UserRest user;

    private ExpousureRest expousure;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UserRest getUser() {
        return user;
    }

    public void setUser(UserRest user) {
        this.user = user;
    }

    public ExpousureRest getExpousure() {
        return expousure;
    }

    public void setExpousure(ExpousureRest expousure) {
        this.expousure = expousure;
    }
}
