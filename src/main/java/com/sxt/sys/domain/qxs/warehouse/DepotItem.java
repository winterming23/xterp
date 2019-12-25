package com.sxt.sys.domain.qxs.warehouse;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 单据子表
 */
@NoArgsConstructor
@Data
public class DepotItem {
    private Integer id;//主键 /null
    private Integer header;//单据主表id
    private Integer materialId;//材料id/商品id
    private Integer basicNumber;//基础数量
    private Double unitPrice;//单价
    private Double allPrice;//总金额
    private String remark;//描述
    private String img;//样品图
    private Integer depotId;//仓库id
    private String deleteFlag;//标记删除

    public DepotItem(Integer id, Integer header, Integer materialId,
                     Integer basicNumber, Double unitPrice,
                     String remark, String img, Integer depot,
                     String deleteFlag) {
        this.id = id;
        this.header = header;
        this.materialId = materialId;
        //this.mUnit = mUnit;
        this.basicNumber = basicNumber;
        this.unitPrice = unitPrice;
        //计算总金额
        this.allPrice = this.basicNumber*this.unitPrice;
        this.remark = remark;
        this.img = img;
        this.depotId = depot;
        //this.mType = mType;
        this.deleteFlag = deleteFlag;
    }

    /**
     * 修改数量的构造方法
     * @param basicNumber
     * @param materialId
     */
    public DepotItem(Integer basicNumber,Integer materialId){
        this.basicNumber=basicNumber;
        this.materialId=materialId;
    }
    public DepotItem(String deleteFlag,Integer id){
        this.deleteFlag=deleteFlag;
        this.id=id;
    }
}
