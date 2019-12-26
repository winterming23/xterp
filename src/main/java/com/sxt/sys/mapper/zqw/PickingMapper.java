package com.sxt.sys.mapper.zqw;


import com.sxt.sys.domain.zqw.Picking;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 * 领料持久层操作接口
 */
public interface PickingMapper {
    /**
     * 查询领料信息
     * @return
     */
    @Select("select picking.*,sys_user.name,productionplan.*,product.product_name FROM picking,productionplan,product,sys_user where \n" +
            "productionplan.id=picking.productionid and productionplan.personCharge=sys_user.id and productionplan.productId=product.id")
    List<HashMap> selePicking();

    /**
     * 添加领料信息
     * @param picking
     * @return
     */
    @Insert("insert into picking(id,pickingNo,startTime,productionAudit,deletePick,productionid) values (null,#{pickingNo},#{startTime},#{productionAudit},#{deletePick},#{productionid})")
    boolean inserPicking(Picking picking);

    /**
     * 修改领料信息
     * @param picking
     * @return
     */
    boolean updatePicking(Picking picking);

    /**
     * 根据id删除领料信息
     * @param id
     * @return
     */
    boolean deletePicking(int id, int deletePick);

    /**
     * 修改领料的审核状态
     * @param id
     * @param productionAudit
     * @return
     */
    @Update("update picking set productionAudit=#{productionAudit} where id=#{id}")
    boolean pickingSh(@Param("id") int id, @Param("productionAudit") int productionAudit);

    /**
     * 根据生产id查询领料数据
     * @param id
     * @return
     */
    @Select("select productionplan.*,number.id AS piid,number.*, materials.* from productionplan,number,materials where\n" +
            "productionplan.pickingid=number.pickid and number.materialsId=materials.id")
    List<HashMap> seleDgpick(int id);

    /**
     * 根据生产id查询名字
     * @param id
     * @return
     */
    @Select("SELECT productionplan.*,sys_user.name FROM productionplan,sys_user \n" +
            "where productionplan.personCharge=sys_user.id")
    List<HashMap> seleDguser(int id);

    /**
     * 根据生产id查询所需生产的名称
     * @param id
     * @return
     */
    @Select("SELECT productionplan.*,product.product_name from productionplan,product\n" +
            "where productionplan.productId=product.id")
    List<HashMap> seleDgProduct(int id);
}
