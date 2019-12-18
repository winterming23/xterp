package com.sxt.sys.domain.qxs.finance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 账号信息
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Account {

    private Integer id;//主键
    private Integer supplier;//客户/供应商编号
    private String cardNumber;//卡号
    private Date dateCreated;//创建日期
    private String remark;// 描述
    private Integer deleteFlag;//标记删除 0：未删除；1：已删除； 默认 0
}
