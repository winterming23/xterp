package com.sxt.sys.domain.zqw;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Assemble {
  private Integer id;//编号
  private String workingProcedure;//工序类别
  private Integer prpersonnel;//生产人员id
  private String productionWorkshop;//生产车间
  private Date assembyTime;//开始时间
  private Date assembyendTime;//预计结束时间
  private Integer qualityTesting;//审核
  private Integer proid;//生产编号

}
