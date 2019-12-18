package com.sxt.sys.domain.qxs.finance;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 财政支出明细实体
 */
@NoArgsConstructor
@Data
public class Expenditure {

    private Integer expenditureId;//支出编号
    private String type;//支出类型；1：采购；2：仓库存储费/搬运费；3：员工工资；0：默认
    private Double paymentAmount;//应付金额
    private Double actualPayment;//实付金额
    private Double balancePayment;//尾款
    private Date dateReceopt;//出账时间；再确认付款以后填写
    private String account;//收款方账号，外键，账户信息表
    private Integer confirmPayment;//是否确认付款；0：未付款；1：已付款；默认0
    private String remark;//描述
    private Integer principal;//负责人，用户id
    private Integer deleteFlag;//标记删除；0：未删除；1：已删除
    private Integer stateClose;//结算状态 0 未结算 1 已结算

    public Expenditure(Integer expenditureId, String type,
                       Double payment_amount, Double actual_payment,
                       Date date_payment, String account,
                       Integer confirm_payment, String remark, Integer principal, Integer deleteFlag, Integer stateClose) {
        this.expenditureId = expenditureId;
        this.type = type;
        this.paymentAmount = payment_amount;
        this.actualPayment = actual_payment;
        //计算尾款
        this.balancePayment = this.paymentAmount-this.actualPayment;
        this.dateReceopt = date_payment;
        this.account = account;
        this.confirmPayment = confirm_payment;
        this.remark = remark;
        this.principal = principal;
        this.deleteFlag = deleteFlag;
        this.stateClose = stateClose;
    }
}
