package com.sxt.sys.domain.zqw;


public class Number {

  private long id;//主键
  private String numberzb;//主板数量
  private String numbercpu;//cpu数量
  private String numbernc;//内存条数量
  private String numberxk;//显卡数量
  private String numberyp;//硬盘数量
  private String numbergtyp;//固态硬盘数量
  private long pickid;//领料编号


  @Override
  public String toString() {
    return "Number{" +
            "id=" + id +
            ", numberzb='" + numberzb + '\'' +
            ", numbercpu='" + numbercpu + '\'' +
            ", numbernc='" + numbernc + '\'' +
            ", numberxk='" + numberxk + '\'' +
            ", numberyp='" + numberyp + '\'' +
            ", numbergtyp='" + numbergtyp + '\'' +
            ", pickid=" + pickid +
            '}';
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNumberzb() {
    return numberzb;
  }

  public void setNumberzb(String numberzb) {
    this.numberzb = numberzb;
  }

  public String getNumbercpu() {
    return numbercpu;
  }

  public void setNumbercpu(String numbercpu) {
    this.numbercpu = numbercpu;
  }

  public String getNumbernc() {
    return numbernc;
  }

  public void setNumbernc(String numbernc) {
    this.numbernc = numbernc;
  }

  public String getNumberxk() {
    return numberxk;
  }

  public void setNumberxk(String numberxk) {
    this.numberxk = numberxk;
  }

  public String getNumberyp() {
    return numberyp;
  }

  public void setNumberyp(String numberyp) {
    this.numberyp = numberyp;
  }

  public String getNumbergtyp() {
    return numbergtyp;
  }

  public void setNumbergtyp(String numbergtyp) {
    this.numbergtyp = numbergtyp;
  }

  public long getPickid() {
    return pickid;
  }

  public void setPickid(long pickid) {
    this.pickid = pickid;
  }

  public Number() {
  }

  public Number(long id, String numberzb, String numbercpu, String numbernc, String numberxk, String numberyp, String numbergtyp, long pickid) {
    this.id = id;
    this.numberzb = numberzb;
    this.numbercpu = numbercpu;
    this.numbernc = numbernc;
    this.numberxk = numberxk;
    this.numberyp = numberyp;
    this.numbergtyp = numbergtyp;
    this.pickid = pickid;
  }
}
