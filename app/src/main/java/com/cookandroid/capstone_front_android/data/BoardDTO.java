package com.cookandroid.capstone_front_android.data;

import android.location.Location;

import org.w3c.dom.Comment;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;

public class BoardDTO {
    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private String content;
    private int viewCount;
    private List<Comment> comments;
    private Location location;
    private Member member;


    public BoardDTO(Long id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Long getId() { return id; }
    public String getTitle(){
        return title;
    }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public String getContent(){ return content; }
    public int getViewCount() { return viewCount; }
    public List<Comment> getComments() { return comments; }
    public Location getLocation() { return location; }
    public Member getMember() { return member; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title){ this.title = title; }
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
    public void setContent(String content){ this.content = content; }
    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
    public void setComments(List<Comment> comments) { this.comments = comments; }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void setMember(Member member) { this.member = member; }

}
