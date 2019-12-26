package com.sxt.sys.domain.zqw;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dispatchedworker {
  private long id;//主键id
  private long dispatchedNo;//派工人id
  private Date dispatchingTime;//派工时间
  private long dispatchedAudits;//派工审核是否通过 0-通过 1-不通过
  private long deleteDw;//删除标记
  private long pickingid;//领料id
  private String billid;//单据编号

}