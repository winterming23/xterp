package com.sxt.sys.domain.qxs.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 单据主表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Depothead {
    private Integer id;//单据id /null
    private String type;//类型：出库(成品，零件)/入库(成品,零件)
    private String number;//票据号
    private String operPersonName;//操作人姓名 /仓库管理员
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间 /获取当前时间
    private Date operTime;//出入库时间 /不需要添加
    private Integer organId;//销售编号
    private Integer handsPersonId;//经手人id：销售/采购 /派工人
    private Integer accountid;//外键 付款账号 //不添加
    private double changeAmount;//单价
    private Double totalPrice;//合计金额
    private String payType;//付款方式 //不添加
    private String remark;//描述
    private Integer accountDay;//存储天数
    private Integer status;//单据状态 1 已审核 0未审核 2：不通过 默认 0 其他数值为待审核
    private String deleteFlag;//标记删除 1 删除 0 未删除 默认 0
    private Integer materialId;//材料id/商品id
    private Integer amount;//数量

    public Depothead(Integer id){
        this.id=id;
        Date date = new Date();
        System.err.println(date);
        this.createTime=date;
    }

    public Depothead(Integer status,Integer id,String deleteFlag){
        this.status=status;
        this.id=id;
        this.deleteFlag=deleteFlag;
    }
}
