package com.lqf.wxdoctor.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Answer {

  private long id;
  private long qid;
  private String content;
  private long createdUser;
  @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss")
  private java.sql.Timestamp createdTime;
  private String name;

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


  public long getQid() {
    return qid;
  }

  public void setQid(long qid) {
    this.qid = qid;
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

}
