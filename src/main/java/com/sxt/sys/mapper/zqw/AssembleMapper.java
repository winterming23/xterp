package com.sxt.sys.mapper.zqw;


import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.zqw.Assemble;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import java.util.HashMap;
import java.util.List;

/**
 * 组装持久层操作接口
 */
public interface AssembleMapper {
    /**
     * 添加组装信息
     * @param assemble
     * @return
     */
    @Insert("insert into assemble(id,workingProcedure,prpersonnel,productionWorkshop,assembyTime,assembyendTime,qualityTesting,proid) values(null,#{workingProcedure},#{prpersonnel},#{productionWorkshop},#{assembyTime},#{assembyendTime},#{qualityTesting},#{proid})")
    boolean inserAssemble(Assemble assemble);

    /**
     * 查询组装信息
     * @return
     */
    @Select("SELECT assemble.*,sys_user.*,product.*,dispatchedworker.billid,productionplan.* from assemble,sys_user,product,productionplan,dispatchedworker\n" +
            "where assemble.proid=productionplan.id and productionplan.productId=product.id and assemble.prpersonnel=sys_user.id and dispatchedworker.pickingid=productionplan.id")
    List<HashMap> seleAssem();

    /**
     * 根据id查询单据编号
     * @param id
     * @return
     */
    List<HashMap> seledepothead(int id);

    /**
     * 根据id查询生产名称
     * @param id
     * @return
     */
    @Select("SELECT product.product_name,productionplan.id as proid FROM product,picking,productionplan,dispatchedworker WHERE\n" +
            "dispatchedworker.pickingid=picking.id and picking.productionid=productionplan.id AND\n" +
            "productionplan.productId=product.id")
    List<HashMap> seleProi(int id);

    /**
     * 根据派工的票据号查询单据状态
     * @param id
     * @return
     */
    @Select("SELECT depothead.*,dispatchedworker.id as deid, number.* from number,depothead,dispatchedworker WHERE\n" +
            " depothead.number=dispatchedworker.billid and number.materialsId=depothead.materialId")
    List<HashMap> seleDep(int id);

    /**
     * 质检
     * @param id
     * @param qualityTesting
     * @return
     */
    @Update("update assemble set qualityTesting=#{qualityTesting} where id=#{id}")
    boolean AssemSh(@Param("id") int id, @Param("qualityTesting") int qualityTesting);

    /**
     * 添加入库单据
     * @param depothead
     * @return
     */
    @Insert("insert into depothead(id,type,number,operPersonName,createTime,operTime,organId,handsPersonId,accountid,changeAmount,totalPrice,payType,remark,accountDay,status,deleteFlag,materialId,amount) values (null,#{type},#{number},#{operPersonName},#{createTime},#{operTime},#{organId},#{handsPersonId},#{accountid},#{changeAmount},#{totalPrice},#{payType},#{remark},#{accountDay},#{status},#{deleteFlag},#{materialId},#{amount})")
    boolean inserDepths(Depothead depothead);
}
