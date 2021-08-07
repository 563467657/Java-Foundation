package com.example.jpatest.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "sys_role", schema = "test", catalog = "")
public class SysRoleEntity {
  private long id;
  private String name;
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
  @Column(name = "name")
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
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
    SysRoleEntity that = (SysRoleEntity) o;
    return id == that.id &&
        Objects.equals(name, that.name) &&
        Objects.equals(remark, that.remark) &&
        Objects.equals(createDate, that.createDate) &&
        Objects.equals(updateDate, that.updateDate);
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, name, remark, createDate, updateDate);
  }
}
