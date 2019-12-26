package com.sxt.sys.domain.zqw;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picking {
  private Integer id;//主键id
  private Integer pickingNo;//领料编号
  private Date startTime;//出库时间
  private Integer productionAudit;//物料审核是否通过 0-通过 1-不通过
  private Integer deletePick;//删除标记
  private Integer productionid;

}
