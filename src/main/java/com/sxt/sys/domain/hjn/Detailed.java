package com.sxt.sys.domain.hjn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xterp
 * @description: 采购订单明细表
 * @author: hjn
 * @create: 2019-12-03 10:46
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detailed {
    private Integer detailed_id;//主键id
    private Integer orderid ;//外键订单表id
    private Integer storehouseid;//外键 仓库id
    private Integer goodsid;//外键 商品明细
    private Integer number;//商品数量
    private Integer tax;//税率(%)
    private Integer price;//商品单价
    private String remarks;//备注
}
