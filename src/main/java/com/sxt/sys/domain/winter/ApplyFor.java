package com.sxt.sys.domain.winter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author Winter
 * @Date 2019/12/4 16:59
 * 申请实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApplyFor implements Serializable {
    private Integer id;//主键编号
    private String type;//申请类型 仓库成品发货申请；生产计划申请（仓库没有成品的前提下才可以申请）
    private Integer productId;// 产品编号
    private Integer number;//数量
    private String itemDescribe;//物品描述
    private Integer approval;//审批
    private Integer proposer;//申请人
    private Integer auditor;//审核人
    private Integer deleteFlag;//删除标记
}
