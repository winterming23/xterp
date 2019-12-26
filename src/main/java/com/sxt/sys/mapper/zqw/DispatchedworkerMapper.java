package com.sxt.sys.mapper.zqw;

;
import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.zqw.Dispatchedworker;
import com.sxt.sys.domain.zqw.Number;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 * 派工持久层操作接口
 */
public interface DispatchedworkerMapper {
    /**
     * 查询所有派工信息
     * @return
     */
    @Select("SELECT dispatchedworker.*,productionplan.*,productionplan.pickingid as propickid, picking.*,product.product_name,sys_user.name from \n" +
            "dispatchedworker,productionplan,picking,product,sys_user\n" +
            "where dispatchedworker.pickingid=picking.id and productionplan.productId=product.id and dispatchedworker.dispatchedNo=sys_user.id")
    List<HashMap> seleDw();

    /**
     * 根据id查询派工状态
     * @param id
     * @return
     */
    @Select("select dispatchedAudits from dispatchedworker where id=#{id}")
    List<Dispatchedworker> selediswork(int id);

    /**
     * 添加派工信息
     * @param dispatchedworker
     * @return
     */
    @Insert("insert into dispatchedworker(id,dispatchedNo,dispatchingTime,dispatchedAudits,deleteDw,pickingid,billid) " +
            "values(null,#{dispatchedNo},#{dispatchingTime},#{dispatchedAudits},#{deleteDw},#{pickingid},#{billid})")
    boolean inserDw(Dispatchedworker dispatchedworker);

    /**
     * 修改派工信息
     * @param dispatchedworker
     * @return
     */
    boolean updateDw(Dispatchedworker dispatchedworker);

    /**
     * 根据id删除派工信息
     * @param id
     * @return
     */
    boolean deleltDw(int id);

    /**
     * 添加仓库单据主表
     * @param depothead
     * @return
     */
    @Insert("insert into depothead(id,type,number,operPersonName,createTime,operTime,organId,handsPersonId,accountId,changeAmount,totalPrice,payType,remark,accountDay,status,deleteFlag,materialId,amount) values (null,#{type},#{number},#{operPersonName},#{createTime},#{operTime},#{organId},#{handsPersonId},#{accountId},#{changeAmount},#{totalPrice},#{payType},#{remark},#{accountDay},#{status},#{deleteFlag},#{materialId},#{amount})")
    boolean inserDepth(Depothead depothead);

    /**
     * 根据id查询领料数据
     * @param id
     * @return
     */
    @Select("select productionplan.*,number.*,materials.*,dispatchedworker.id as disid,dispatchedworker.* from dispatchedworker,productionplan,number,materials,picking where \n" +
            "productionplan.pickingid=number.pickid and number.materialsId=materials.id and dispatchedworker.pickingid=picking.id and picking.productionid=productionplan.id")
    List<HashMap> seleppik(int id);

    /**
     * 修改派工的审核状态
     * @param id
     * @param dispatchedAudits
     * @return
     */
    @Update("update dispatchedworker set dispatchedAudits=#{dispatchedAudits} where id=#{id}")
    boolean dispathSh(@Param("id") int id, @Param("dispatchedAudits") int dispatchedAudits);

    /**
     * 根据生产编号查询数量信息
     * @param pickingid
     * @return
     */
    @Select("select * from number where pickid=#{pickid}")
    List<Number> seleDisNumber(int pickingid);

    /**
     * 根据单据子表id查询数据
     * @param id
     * @return
     */
    @Select("select * from materials where id=#{id}")
    List<Materials> seleMater(int id);

    /**
     * 根据id查询派工信息
     * @param id
     * @return
     */
    @Select("select * from dispatchedworker where id=#{id}")
    List<Dispatchedworker> seleDiskker(int id);
}
