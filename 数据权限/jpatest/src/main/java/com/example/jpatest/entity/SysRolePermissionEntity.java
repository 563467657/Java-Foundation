package com.example.jpatest.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "sys_role_permission", schema = "test", catalog = "")
public class SysRolePermissionEntity {
  private long id;
  private long roleId;
  private long permissionId;
  
  @Id
  @Column(name = "id")
  public long getId() {
    return id;
  }
  
  public void setId(long id) {
    this.id = id;
  }
  
  @Basic
  @Column(name = "role_id")
  public long getRoleId() {
    return roleId;
  }
  
  public void setRoleId(long roleId) {
    this.roleId = roleId;
  }
  
  @Basic
  @Column(name = "permission_id")
  public long getPermissionId() {
    return permissionId;
  }
  
  public void setPermissionId(long permissionId) {
    this.permissionId = permissionId;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    SysRolePermissionEntity that = (SysRolePermissionEntity) o;
    return id == that.id &&
        roleId == that.roleId &&
        permissionId == that.permissionId;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(id, roleId, permissionId);
  }
}
