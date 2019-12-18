
package com.sxt.sys.mapper.zqw;
import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;
import com.sxt.sys.domain.zqw.Userinfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * 生产计划持久层操作接口
 */
@Repository
public interface ProductionplanMapper {
    /**
     * 查询生产计划
     * @return
     */
    @Select("SELECT productionPlan.*,product.product_name,userInfo.userName,product_model.* FROM productionPlan,product,userInfo,picking,product_model where\n" +
            "productionPlan.productId=product.id and productionPlan.personCharge=userInfo.id and picking.pickingNo=product_model.id")
    List<HashMap> seleProuct();
    /**
     * 添加一个生产计划
     * @param productionplan
     * @return
     */
    @Insert("insert into productionplan(id,productId,startTime,endTime,personCharge,productionAudit,deleteProd,prquantity) values " +
            "(null,#{productId},#{startTime},#{endTime},#{personCharge},#{productionAudit},#{deleteProd},#{prquantity})")
    boolean inserProuct(Productionplan productionplan);

    /**
     * 修改生产计划
     * @param productionplan
     * @return
     */
    boolean updatePeouct(Productionplan productionplan);

    /**
     * 根据id删除生产计划
     * @param id
     * @return
     */
    @Update("update productionplan set deleteProd=#{deleteProd} where id=#{id}")
    boolean deletePeouct(@Param("deleteProd") int deleteProd, @Param("id") int id);

    /**
     * 根据申请表查询产品名称
     * @return
     */
    @Select("select * from product")
    List<HashMap> dgselepro();

    /**
     * 查询产品信息
     * @return
     */
    @Select("select product_model.*,number.*,productionplan.id as prid from productionplan,number,product_model WHERE number.pickid=productionplan.id")
    List<HashMap> selepm();

    /**
     * 查询产品信息
     * @return
     */
    @Select("select * from product_model")
    List<Product_model> selepmll();

    /**
     * 添加领料信息
     * @param picking
     * @return
     */
    @Insert("insert into picking(id,pickingNo,startTime,productionAudit,deletePick,productionid) values " +
            "(null,#{pickingNo},#{startTime},#{productionAudit},#{deletePick},#{productionid})")
    boolean inserpick(Picking picking);

    /**
     * 查询用户姓名
     * @return
     */
    @Select("select * from userinfo")
    List<Userinfo> seleuser();

    /**
     * 根据生产编号查询所需物料
     * @return
     */
    @Select("select productionplan.pickingid,picking.*,number.*,product_model.* from productionplan,picking,number,product_model\n" +
            "where productionplan.pickingid=picking.productionid and Number.pickid=picking.id and picking.pickingNo=product_model.id")
    List<HashMap> selepropick(int pickingid);

}
