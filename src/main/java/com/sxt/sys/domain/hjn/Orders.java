package com.sxt.sys.domain.hjn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @program: xterp
 * @description: 采购订单表
 * @author: hjn
 * @create: 2019-12-03 10:07
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    private Integer id;//主键id
    private Integer orderid;//采购订单编号
    private Integer  supplierid;//外键,供应商
    private Integer purchaserid;//外键,采购员
    private Integer rebate;//折扣率(%)
    private Integer paytype;//外键,付款方式
    private Integer total;//订单总金额
    private Integer  amount_paid ;//已付金额
    private Integer total_tax;//价税合计
    private Integer  orderstate;//外键,订单审核
    private Integer preferential;//优惠金额
    private Integer costid; //外键,采购支出表
    private Date createtime;//创建时间
    private Integer finance;//财务记录添加状态

    public Orders(Integer orderid){
        this.orderid=orderid;
        this.createtime=new Date();
    }
}
