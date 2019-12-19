package com.sxt.sys.domain.hjn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xterp
 * @description: 采购支出表
 * @author: hjn
 * @create: 2019-12-03 09:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cost {
    private int id;//主键 id
    private int orderid; //外键,关联采购订单表id
    private String costtype;//支出类型,住宿,车费等
    private int costprice;//支出费用金额
}
