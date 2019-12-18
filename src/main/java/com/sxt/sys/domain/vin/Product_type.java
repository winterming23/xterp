package com.sxt.sys.domain.vin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 产品类别
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product_type {
    private long id;//主键
    private String type_name;//产品类别名
    private String remake;//产品状态
    private String delete_Flag;//删除标记（0==未删除,1==删除)
//    private Product productType;
}
