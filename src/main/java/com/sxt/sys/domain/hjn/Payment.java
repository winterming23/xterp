package com.sxt.sys.domain.hjn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: xterp
 * @description: 付款方式表
 * @author: hjn
 * @create: 2019-12-03 10:52
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private int id;//主键
    private String type;//付款方式
}
