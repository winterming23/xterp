package com.sxt.sys.mapper.zqw;


import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.zqw.Number;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * 所需物料数量持久层操作接口
 */
public interface NumberMapper {
    /**
     * 添加所需物料数量
     * @param number
     * @return
     */
    @Insert("insert into number(id,materialsId,numbersl,pickid,catname) values (null,#{materialsId},#{numbersl},#{pickid},#{catname})")
    Boolean inserNum(Number number);

    /**
     * 查询材料编号信息
     * @return
     */
    @Select("select * from materials where mtype='零件'")
    List<Materials> seleMat();

    /**
     * 查询领料信息
     * @return
     */
    @Select("select * from Picking")
    List<Picking> selepicking();

    /**
     * 查询生产计划
     * @return
     */
    @Select("select * from productionplan")
    List<Productionplan> seleProduct();

    /**
     * 根据根据子表和生产查询id
     * @return
     */
    @Select("select picking.id from picking,productionplan,number where picking.id=productionplan.pickingid and picking.id=number.pickid")
    List<HashMap> selePadd();
}
