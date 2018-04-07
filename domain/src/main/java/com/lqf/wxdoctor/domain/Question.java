package com.lqf.wxdoctor.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Question {

  private long id;
  @JsonAlias("title")
  private String title;
  @JsonAlias("content")
  private String content;
  private long createdUser;
  private String name;
  @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp modifiedTime;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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


  public long getCreatedUser() {
    return createdUser;
  }

  public void setCreatedUser(long createdUser) {
    this.createdUser = createdUser;
  }


  public java.sql.Timestamp getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(java.sql.Timestamp createdTime) {
    this.createdTime = createdTime;
  }


  public java.sql.Timestamp getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(java.sql.Timestamp modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

}
