package com.sxt.sys.domain.vin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 供应商/客户
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    private long id;//主键
    private String supplier;//供应商，客户，名称
    private String contacts;//联系人
    private String phonenum;//联系电话
    private String email;//电子邮箱
//    private String isystem;//是否系统自带 0==自带 1==非系统
    private String type;//类型(供应商，客户）
    private int enabled;//启用状态
    private Double advanceIn;//预收款
    private Double beginNeedGet;//期初应收
    private Double beginNeedPay;//期初应付
    private Double allNeedGet;//累计应收
    private Double allNeedPay;//累计应付
    private String fax;//传真
    private String telephone;//手机号码
    private String address;//地址
    private String taxNum;//纳税人识别号
    private String bankName;//开户行
    private String accountNumber;//账号
    private Double taxRate;//税率
    private String delete_flag;//删除标记(默认0)
    private String status;//审核状态，默认0，0未审核，1已审核
}
