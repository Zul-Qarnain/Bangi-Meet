package com.meetingapp.models;

public class Meeting {
    private String id;
    private String title;
    private String time;
    private String description;
    private String link;
    
    public Meeting() {
    }
    
    public Meeting(String id, String title, String time) {
        this.id = id;
        this.title = title;
        this.time = time;
    }
    
    public Meeting(String id, String title, String time, String description, String link) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.description = description;
        this.link = link;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getLink() {
        return link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
}
