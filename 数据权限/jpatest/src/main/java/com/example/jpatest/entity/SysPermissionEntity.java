package com.example.jpatest.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sys_permission", schema = "test", catalog = "")
public class SysPermissionEntity {
  private long id;
  private Long parentId;
  private String name;
  private String code;
  private String url;
  private int type;
  private String icon;
  private String remark;
  private Timestamp createDate;
  private Timestamp updateDate;
  
  @Id
  @Column(name = "id")
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  @Basic
  @Column(name = "parent_id")
  public Long getParentId() {
    return parentId;
  }
  
  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }
  
  @Basic
  @Column(name = "name")
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  @Basic
  @Column(name = "code")
  public String getCode() {
    return code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  @Basic
  @Column(name = "url")
  public String getUrl() {
    return url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  @Basic
  @Column(name = "type")
  public int getType() {
    return type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  @Basic
  @Column(name = "icon")
  public String getIcon() {
    return icon;
  }
  
  public void setIcon(String icon) {
    this.icon = icon;
  }
  
  @Basic
  @Column(name = "remark")
  public String getRemark() {
    return remark;
  }
  
  public void setRemark(String remark) {
    this.remark = remark;
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
    SysPermissionEntity that = (SysPermissionEntity) o;
    return id == that.id &&
        type == that.type &&
        Objects.equals(parentId, that.parentId) &&
        Objects.equals(name, that.name) &&
        Objects.equals(code, that.code) &&
        Objects.equals(url, that.url) &&
        Objects.equals(icon, that.icon) &&
        Objects.equals(remark, that.remark) &&
        Objects.equals(createDate, that.createDate) &&
        Objects.equals(updateDate, that.updateDate);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, parentId, name, code, url, type, icon, remark, createDate, updateDate);
  }
}
