package com.example.databasefinalhomework;

import java.time.LocalDateTime;

public class Announcement {
    private String publisher;
    private String title;
    private String content;
    private LocalDateTime publishTime;
    private String level;

    public Announcement(String publisher, String title, String content, LocalDateTime publishTime, String level) {
        this.publisher = publisher;
        this.title = title;
        this.content = content;
        this.publishTime = publishTime;
        this.level = level;
    }

    public Announcement(String publisher, String title, String content, LocalDateTime publishTime) {
        this.publisher = publisher;
        this.title = title;
        this.content = content;
        this.publishTime = publishTime;
        this.level = "";
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public LocalDateTime getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "发布时间: " + publishTime + "\n发布者: " + publisher + "\n标题: " + title + "\n正文: " + content;
    }
}
