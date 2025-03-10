package com.axedBackend.AxedBackend.models;

public class Notes {
    private String id;
    private String userId;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private boolean favorite;
    // TODO LATER: Add tags and add sketch

    public Notes() {

    }

    public Notes(String id, String userId, String title, String content, String createdAt, String updatedAt,
            boolean favorite) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.favorite = favorite;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}