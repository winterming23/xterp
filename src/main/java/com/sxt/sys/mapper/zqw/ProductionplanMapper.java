
package com.sxt.sys.mapper.zqw;


import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.winter.ApplyFor;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;
import com.sxt.sys.domain.zqw.SysUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


import java.util.HashMap;
import java.util.List;

/**
 * 生产计划持久层操作接口
 */

public interface ProductionplanMapper {
    /**
     * 查询生产计划
     * @return
     */
    @Select(" SELECT productionPlan.*,product.product_name,sys_user.name FROM productionPlan,product,sys_user\n" +
            "where productionPlan.productId=product.id and productionPlan.personCharge=sys_user.id ")
    List<HashMap> seleProuct();
    /**
     * 添加一个生产计划
     * @param productionplan
     * @return
     */
    @Insert("insert into productionplan(id,productId,startTime,endTime,personCharge,productionAudit,deleteProd,pickingid,quantity,salesid) values " +
            "(null,#{productId},#{startTime},#{endTime},#{personCharge},#{productionAudit},#{deleteProd},#{pickingid},#{quantity},#{salesid})")
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
    @Select("select * from product where id=#{id}")
    List<HashMap> dgselepro(int productId);

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
    @Insert("insert into picking(id,pickingNo,startTime,productionAudit,deletePick) values " +
            "(null,#{pickingNo},#{startTime},#{productionAudit},#{deletePick})")
    boolean inserpick(Picking picking);

    /**
     * 查询用户姓名
     * @return
     */
    @Select("select * from sys_user")
    List<SysUser> seleuser();

    /**
     * 根据id查询生产名称
     * @param
     * @return
     */
    @Select("select * from sys_user")
    List<SysUser> seleusers();

    /**
     * 根据生产编号查询所需物料
     * @return
     */
    @Select("select productionplan.*,number.*,materials.* from productionplan,number,materials\n" +
            "where productionplan.pickingid=number.pickid and number.materialsId=materials.id")
    List<HashMap> selepropick(int pickingid);

    /**
     * 查询生产和领料数据
     * @return
     */
    @Select("select productionplan.pickingid,picking.*,number.*,product_model.* from productionplan,picking,number,product_model\n" +
            "where productionplan.pickingid=picking.id and Number.pickid=picking.id and picking.pickingNo=product_model.id")
    List<HashMap> seleProckll();

    /**
     * 修改审核状态
     * @param id
     * @param productionAudit
     * @return
     */
    @Update("update productionplan set productionAudit=#{productionAudit} where id=#{id}")
    boolean productSh(@Param("id") int id, @Param("productionAudit") int productionAudit);

    /**
     * 查询申请数据
     * @return
     */
    @Select("select applyfor.*,sys_user.*,product.product_name from applyfor,sys_user,product\n" +
            "where applyfor.productId=product.id and applyfor.proposer=sys_user.id")
    List<HashMap> seleapply();

    /**
     * 审核
     * @param auditor
     * @param salesStatus
     * @return
     */
    @Update("update applyfor set salesStatus=#{salesStatus} where auditor=#{auditor}")
    boolean applySh(@Param("auditor") int auditor, @Param("salesStatus") int salesStatus);

    /**
     * 根据id查询申请数据
     * @param auditor
     * @return
     */
    @Select("select * from applyFor where auditor=#{auditor}")
    List<ApplyFor> seleappfor(int auditor);
}
