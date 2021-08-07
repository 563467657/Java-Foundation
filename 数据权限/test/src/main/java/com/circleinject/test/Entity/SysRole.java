package com.circleinject.test.Entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRole {
    private Long id;
    private String name;
    private String remark;
    private Date createDate;
    private Date updateDate;
}
