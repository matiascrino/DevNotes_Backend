package com.example.backend_java.models.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestModel {

    @NotNull(message = "Title is required")
    @Size(min = 3, max = 255, message = "Title should be between 3 and 255 characters")
    private String title;

    @NotNull(message = "Content is required")
    private String content;

    @Min(value = 1, message = "Exposure ID must be greater than 0")
    private long exposureId;
    @Min(value = 0, message = "Expiration time must be greater than 0")
    private int expiresAt;

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
}
