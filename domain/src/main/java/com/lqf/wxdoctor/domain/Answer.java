package com.lqf.wxdoctor.domain;

public class Answer {

  private long id;
  private long qid;
  private String content;
  private long createdUser;
  private java.sql.Timestamp createdTime;


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
