package com.sxt.sys.domain.zqw;


import java.util.Date;

public class Assemble {

  private long id;//主键id
  private long productId;//产品编号
  private long pickingId;//领料编号
  private Date assembyTime;//组装时间
  private Date assembyendTime;//组装结束时间
  private String assembledProducts;//组装成品
  private long qualityTesting;//质检审核是否通过 0-通过 1-不通过
  private long deleteAsse;//删除标记

  public Assemble() {
  }

  @Override
  public String toString() {
    return "Assemble{" +
            "id=" + id +
            ", productId=" + productId +
            ", pickingId=" + pickingId +
            ", assembyTime=" + assembyTime +
            ", assembyendTime=" + assembyendTime +
            ", assembledProducts='" + assembledProducts + '\'' +
            ", qualityTesting=" + qualityTesting +
            ", deleteAsse=" + deleteAsse +
            '}';
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

  public long getPickingId() {
    return pickingId;
  }

  public void setPickingId(long pickingId) {
    this.pickingId = pickingId;
  }

  public Date getAssembyTime() {
    return assembyTime;
  }

  public void setAssembyTime(Date assembyTime) {
    this.assembyTime = assembyTime;
  }

  public Date getAssembyendTime() {
    return assembyendTime;
  }

  public void setAssembyendTime(Date assembyendTime) {
    this.assembyendTime = assembyendTime;
  }

  public String getAssembledProducts() {
    return assembledProducts;
  }

  public void setAssembledProducts(String assembledProducts) {
    this.assembledProducts = assembledProducts;
  }

  public long getQualityTesting() {
    return qualityTesting;
  }

  public void setQualityTesting(long qualityTesting) {
    this.qualityTesting = qualityTesting;
  }

  public long getDeleteAsse() {
    return deleteAsse;
  }

  public void setDeleteAsse(long deleteAsse) {
    this.deleteAsse = deleteAsse;
  }

  public Assemble(long id, long productId, long pickingId, Date assembyTime, Date assembyendTime, String assembledProducts, long qualityTesting, long deleteAsse) {
    this.id = id;
    this.productId = productId;
    this.pickingId = pickingId;
    this.assembyTime = assembyTime;
    this.assembyendTime = assembyendTime;
    this.assembledProducts = assembledProducts;
    this.qualityTesting = qualityTesting;
    this.deleteAsse = deleteAsse;
  }
}