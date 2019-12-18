package com.sxt.sys.mapper.qxs.warehouse;

import com.sxt.sys.domain.qxs.warehouse.Depothead;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 单据主表数据操作
 */
public interface DepotHeadMapper {

    /**
     * 查询所有未删除的单据主表
     * @return
     */
    @Select("select * from depotHead where deleteFlag!='1' order by createTime desc")
    List<Depothead> queryNotDeleteDepotHead();

    /**
     * 查询所有数据,包括删除的数据
     * @return
     */
    @Select("select * from depotHead order by createTime desc")
    List<Depothead> queryAllDepotHead();

    /**
     * 根据票据号查询当前记录
     * @param number
     * @return
     */
    @Select("select * from depotHead where number=#{number} and deleteFlag='1' ")
    Depothead getOneDepotHead(String number);

    /**
     * 查询被删除的数据
     * @return
     */
    @Select("select * from depotHead where deleteFlag='1' order by createTime desc")
    List<Depothead> queryDelete();

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    @Select("select * from depotHead where id=#{id} and deleteFlag='1' ")
    Depothead getDepotHeadID(Integer id);

    /**
     * 新增主表数据
     * @param depothead
     * @return
     */
    @Insert("insert into depotHead values(null,#{type},#{number},#{operPersonName},#{createTime}," +
            "#{operTime},#{organId},#{handsPersonId},#{account},#{changeAmount},#{totalPrice},#{payType}," +
            "#{remark},#{accountDay},0,0,#{materialId},#{amount})")
    boolean addDepotHead(Depothead depothead);

    /**
     * 修改数据
     * @param depothead
     * @return
     */
    @Update("update depotHead set operTime=#{operTime},organId=#{organId},handsPersonId=#{handsPersonId}," +
            "account=#{account},changeAmount=#{changeAmount},totalPrice=#{totalPrice},payType=#{payType}," +
            "remark=#{remark},accountDay=#{accountDay},amount=#{amount} where id=#{id}")
    boolean updateDepotHead(Depothead depothead);

    /**
     * 审批
     * @param status 1:已审核 0:未审核 2：不通过 默认 0 其他数值为待审核
     * @return
     */
    @Update("update depotHead set status=#{status} where id=#{id}")
    boolean depotHeadExamin(Integer status, Integer id);

    /**
     * 标记删除
     * @param deleteFlag 1 删除 0 未删除 默认 0
     * @param id 条件
     * @return
     */
    @Update("update depotHead set deleteFlag=#{deleteFlag} where id=#{id}")
    boolean deleteDepotHead(String deleteFlag, Integer id);

    /**
     * 子表数据加入失败，执行
     * @param number
     * @return
     */
    @Delete("delete from depotHead where number=#{number}")
    boolean deDepotHeadNumber(String number);
}
