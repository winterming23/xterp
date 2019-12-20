package com.sxt.sys.domain.qxs.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 仓库详情
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Depot {
    private Integer id;//编号，主键
    private String name;//仓库名称
    private String address;//仓库地址
    private Double truckage;//搬运费/存储费
    private String type;//类型
    private String sort;//排序
    private String remark;//描述
    private Integer principal;//仓库负责人,用户id
    private Integer deleteFlag;//标记删除 0 未删除；1 已删除 默认 0
    private Integer isDefault;//是否默认，0 默认 1 不默认；默认为1

    public Depot(Integer deleteFlag,Integer isDefault,Integer id){
        this.id=id;
        this.deleteFlag=deleteFlag;
        this.isDefault=isDefault;
    }
}
