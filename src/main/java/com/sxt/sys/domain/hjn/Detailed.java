package com.sxt.sys.domain.hjn;

/**
 * @program: xterp
 * @description: 采购订单明细表
 * @author: hjn
 * @create: 2019-12-03 10:46
 **/
public class Detailed {
    private int id;//主键id
    private int orderid ;//外键订单表id
    private int storehouseid;//外键 仓库id
    private int goodsid;//外键 商品明细
    private int number;//商品数量
    private int tax;//税率(%)
    private int price;//商品单价
    private String remarks;//备注
}
