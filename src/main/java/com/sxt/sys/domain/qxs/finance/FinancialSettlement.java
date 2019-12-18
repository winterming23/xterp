package com.sxt.sys.domain.qxs.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 财务结算明细
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FinancialSettlement {

    private Integer id;//编号
    private Double totalMoney;//总金额
    private Date balanceDate;//结算日期
    private Integer type;//结算类型，1：支出结算；2：收入结算；默认0
    private Integer userId;//操作人，用户id
    private String remark;//描述
    private Integer deleteFlag;//标记删除
}
