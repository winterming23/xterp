package com.sxt.sys.domain.zqw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Number {

  private long id;//主键
  private Integer materialsId;//材料编号
  private String numbersl;//数量
  private long pickid;//领料编号


}
