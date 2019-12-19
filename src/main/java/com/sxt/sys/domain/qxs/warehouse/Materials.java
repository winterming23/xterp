package com.sxt.sys.domain.qxs.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xterp
 * @description: 材料表
 * @author: snow
 * @create: 2019-12-16 13:44
 **/
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Materials {

    private Integer id;
    private String mName;//类型名称
    private String remark;//描述
    private String mType;//类型:成品/材料
    private String mUnit;//计量单位
    private Double price;//单价
    private String designation;//材料名称
}
