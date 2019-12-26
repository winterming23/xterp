package com.sxt.sys.domain.zqw;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Productionplan {

  private Integer id;//主键id
  private long productId;//产品编号
  private Date startTime;//开始时间
  private Date endTime;//预计结束时间
  private long personCharge;//负责人id
  private long productionAudit;//生产审核是否通过 0-通过 1-不通过
  private long deleteProd;//删除标记
  private long pickingid;//领料编号
  private Integer quantity;//生产数量
  private Integer salesid;//销售编号

}