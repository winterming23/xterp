package com.sxt.sys.domain.qxs.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 财务汇总实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Summarizing {
    private Integer id;//汇总编号
    private Integer financialSettlement;//财务结算编号
    private Date collectDate;//汇总时间
    private Integer principal;//用户id 外键 操作人
    private String describe;//描述
    private Integer deleteFlag;//标记删除 0：不删除；1：删除； 默认0

}
