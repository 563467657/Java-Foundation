package com.circleinject.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.circleinject.test.Entity.SysRole;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper extends BaseMapper<SysRole> {
  
  public SysRole findByUserId(@Param("userId") Long userId);

}
