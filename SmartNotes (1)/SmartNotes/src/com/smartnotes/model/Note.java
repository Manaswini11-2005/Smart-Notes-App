package com.smartnotes.model;

import java.sql.Timestamp;

public class Note {
    private int id;
    private int userId;
    private String title;
    private String content;
    private String tags;
    private boolean pinned;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Note() {}

    public Note(int userId, String title, String content, String tags) {
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.tags = tags;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getTags() { return tags; }
    public void setTags(String tags) { this.tags = tags; }

    public boolean isPinned() { return pinned; }
    public void setPinned(boolean pinned) { this.pinned = pinned; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
