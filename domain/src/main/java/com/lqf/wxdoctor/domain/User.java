package com.lqf.wxdoctor.domain;


public class User {

  private long id;
  private String name;
  private long blh;
  private String openid;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp modifiedTime;
  private long isAdmin;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getBlh() {
    return blh;
  }

  public void setBlh(long blh) {
    this.blh = blh;
  }


  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
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


  public long getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(long isAdmin) {
    this.isAdmin = isAdmin;
  }

}
