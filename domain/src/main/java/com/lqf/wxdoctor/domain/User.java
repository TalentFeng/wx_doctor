package com.lqf.wxdoctor.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class User implements UserDetails {

  private int id;
  private String name;
  private int blh;
  private String openId;
  private java.sql.Timestamp createdTime;
  private java.sql.Timestamp modifiedTime;
  private int isAdmin;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public int getBlh() {
    return blh;
  }

  public void setBlh(int blh) {
    this.blh = blh;
  }


  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
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


  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  public String getPassword() {
    return Long.toString(blh);
  }

  public String getUsername() {
    return openId;
  }

  public boolean isAccountNonExpired() {
    return true;
  }

  public boolean isAccountNonLocked() {
    return true;
  }

  public boolean isCredentialsNonExpired() {
    return true;
  }

  public boolean isEnabled() {
    return true;
  }

  public int getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(int isAdmin) {
    this.isAdmin = isAdmin;
  }
}
