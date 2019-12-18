package com.sxt.sys.domain.hjn;

/**
 * @program: xterp
 * @description: 采购订单表
 * @author: hjn
 * @create: 2019-12-03 10:07
 **/
public class Orders {
    private int id;//主键id
    private Integer orderid;//采购订单编号
    private int  supplierid;//外键,供应商
    private int purchaserid;//外键,采购员
    private int rebate;//折扣率(%)
    private int paytype;//外键,付款方式
    private int total;//订单总金额
    private int  amount_paid ;//已付金额
    private int total_tax;//价税合计
    private int  orderstate;//外键,订单审核
    private int preferential;//优惠金额
    private int costid; //外键,采购支出表
    private int createtime;//创建时间
    private Integer finance;//财务记录添加状态
}
