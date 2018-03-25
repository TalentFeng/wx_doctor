package com.lqf.wxdoctor.domain;

public class User {
  private Long id;
  private String name;
  private Long blh;
  private String openid;
  private java.sql.Timestamp created_time;
  private java.sql.Timestamp modified_time;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getBlh() {
    return blh;
  }

  public void setBlh(Long blh) {
    this.blh = blh;
  }

  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  public java.sql.Timestamp getCreated_time() {
    return created_time;
  }

  public void setCreated_time(java.sql.Timestamp created_time) {
    this.created_time = created_time;
  }

  public java.sql.Timestamp getModified_time() {
    return modified_time;
  }

  public void setModified_time(java.sql.Timestamp modified_time) {
    this.modified_time = modified_time;
  }
}
