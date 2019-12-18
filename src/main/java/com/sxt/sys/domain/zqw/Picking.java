package com.sxt.sys.domain.zqw;


import java.util.Date;

public class Picking {
  private long id;//主键id
  private long pickingNo;//领料编号
  private Date startTime;//出库时间
  private long productionAudit;//物料审核是否通过 0-通过 1-不通过
  private long deletePick;//删除标记
  private long productionid;//生产编号

  @Override
  public String toString() {
    return "Picking{" +
            "id=" + id +
            ", pickingNo=" + pickingNo +
            ", startTime=" + startTime +
            ", productionAudit=" + productionAudit +
            ", deletePick=" + deletePick +
            ", productionid=" + productionid +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPickingNo() {
    return pickingNo;
  }

  public void setPickingNo(long pickingNo) {
    this.pickingNo = pickingNo;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public long getProductionAudit() {
    return productionAudit;
  }

  public void setProductionAudit(long productionAudit) {
    this.productionAudit = productionAudit;
  }

  public long getDeletePick() {
    return deletePick;
  }

  public void setDeletePick(long deletePick) {
    this.deletePick = deletePick;
  }

  public long getProductionid() {
    return productionid;
  }

  public void setProductionid(long productionid) {
    this.productionid = productionid;
  }

  public Picking() {
  }

  public Picking(long id, long pickingNo, Date startTime, long productionAudit, long deletePick, long productionid) {
    this.id = id;
    this.pickingNo = pickingNo;
    this.startTime = startTime;
    this.productionAudit = productionAudit;
    this.deletePick = deletePick;
    this.productionid = productionid;
  }
}
