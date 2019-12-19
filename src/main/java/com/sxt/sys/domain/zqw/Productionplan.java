package com.sxt.sys.domain.zqw;


import java.util.Date;

public class Productionplan {

  private long id;//主键id
  private long productId;//产品编号
  private Date startTime;//开始时间
  private Date endTime;//预计结束时间
  private long personCharge;//负责人id
  private long productionAudit;//生产审核是否通过 0-通过 1-不通过
  private long deleteProd;//删除标记
  private long pickingid;//领料编号
  @Override
  public String toString() {
    return "Productionplan{" +
            "id=" + id +
            ", productId=" + productId +
            ", startTime=" + startTime +
            ", endTime=" + endTime +
            ", personCharge=" + personCharge +
            ", productionAudit=" + productionAudit +
            ", deleteProd=" + deleteProd +
            ", pickingid=" + pickingid +
            '}';
  }

  public Productionplan() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getProductId() {
    return productId;
  }

  public void setProductId(long productId) {
    this.productId = productId;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getEndTime() {
    return endTime;
  }

  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  public long getPersonCharge() {
    return personCharge;
  }

  public void setPersonCharge(long personCharge) {
    this.personCharge = personCharge;
  }

  public long getProductionAudit() {
    return productionAudit;
  }

  public void setProductionAudit(long productionAudit) {
    this.productionAudit = productionAudit;
  }

  public long getDeleteProd() {
    return deleteProd;
  }

  public void setDeleteProd(long deleteProd) {
    this.deleteProd = deleteProd;
  }

  public long getPickingid() {
    return pickingid;
  }

  public void setPickingid(long pickingid) {
    this.pickingid = pickingid;
  }

  public Productionplan(long id, long productId, Date startTime, Date endTime, long personCharge, long productionAudit, long deleteProd, long pickingid) {
    this.id = id;
    this.productId = productId;
    this.startTime = startTime;
    this.endTime = endTime;
    this.personCharge = personCharge;
    this.productionAudit = productionAudit;
    this.deleteProd = deleteProd;
    this.pickingid = pickingid;
  }
}