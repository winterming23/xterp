package com.sxt.sys.domain.zqw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser {

  private long id;
  private String name;
  private String loginname;
  private String address;
  private long sex;
  private String remark;
  private String pwd;
  private long deptid;
  private Date hiredate;
  private long mgr;
  private long available;
  private long ordernum;
  private long type;
  private String imgpath;
  private String salt;


}
