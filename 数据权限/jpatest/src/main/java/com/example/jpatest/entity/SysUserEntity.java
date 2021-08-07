package com.example.jpatest.entity;

import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@ToString
@Entity
@Table(name = "sys_user", schema = "test", catalog = "")
public class SysUserEntity {
  private long id;
  private String username;
  private String password;
  private Integer isAccountNonExpired;
  private Integer isAccountNonLocked;
  private Integer isCredentialsNonExpired;
  private Integer isEnabled;
  private String nickName;
  private String mobile;
  private String email;
  private Timestamp createDate;
  private Timestamp updateDate;
  
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  @Basic
  @Column(name = "username")
  public String getUsername() {
    return username;
  }
  
  public void setUsername(String username) {
    this.username = username;
  }
  
  @Basic
  @Column(name = "password")
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Basic
  @Column(name = "is_account_non_expired")
  public Integer getIsAccountNonExpired() {
    return isAccountNonExpired;
  }
  
  public void setIsAccountNonExpired(Integer isAccountNonExpired) {
    this.isAccountNonExpired = isAccountNonExpired;
  }
  
  @Basic
  @Column(name = "is_account_non_locked")
  public Integer getIsAccountNonLocked() {
    return isAccountNonLocked;
  }
  
  public void setIsAccountNonLocked(Integer isAccountNonLocked) {
    this.isAccountNonLocked = isAccountNonLocked;
  }
  
  @Basic
  @Column(name = "is_credentials_non_expired")
  public Integer getIsCredentialsNonExpired() {
    return isCredentialsNonExpired;
  }
  
  public void setIsCredentialsNonExpired(Integer isCredentialsNonExpired) {
    this.isCredentialsNonExpired = isCredentialsNonExpired;
  }
  
  @Basic
  @Column(name = "is_enabled")
  public Integer getIsEnabled() {
    return isEnabled;
  }
  
  public void setIsEnabled(Integer isEnabled) {
    this.isEnabled = isEnabled;
  }
  
  @Basic
  @Column(name = "nick_name")
  public String getNickName() {
    return nickName;
  }
  
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
  
  @Basic
  @Column(name = "mobile")
  public String getMobile() {
    return mobile;
  }
  
  public void setMobile(String mobile) {
    this.mobile = mobile;
  }
  
  @Basic
  @Column(name = "email")
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  @Basic
  @Column(name = "create_date")
  public Timestamp getCreateDate() {
    return createDate;
  }
  
  public void setCreateDate(Timestamp createDate) {
    this.createDate = createDate;
  }
  
  @Basic
  @Column(name = "update_date")
  public Timestamp getUpdateDate() {
    return updateDate;
  }
  
  public void setUpdateDate(Timestamp updateDate) {
    this.updateDate = updateDate;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SysUserEntity that = (SysUserEntity) o;
    return id == that.id &&
        Objects.equals(username, that.username) &&
        Objects.equals(password, that.password) &&
        Objects.equals(isAccountNonExpired, that.isAccountNonExpired) &&
        Objects.equals(isAccountNonLocked, that.isAccountNonLocked) &&
        Objects.equals(isCredentialsNonExpired, that.isCredentialsNonExpired) &&
        Objects.equals(isEnabled, that.isEnabled) &&
        Objects.equals(nickName, that.nickName) &&
        Objects.equals(mobile, that.mobile) &&
        Objects.equals(email, that.email) &&
        Objects.equals(createDate, that.createDate) &&
        Objects.equals(updateDate, that.updateDate);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, isAccountNonExpired, isAccountNonLocked, isCredentialsNonExpired, isEnabled, nickName, mobile, email, createDate, updateDate);
  }
}
