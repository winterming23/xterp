package com.sxt.sys.mapper.hjn;

import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

/**
 * @program: xterp
 * @description: DetailedMapper
 * @author: hjn
 * @create: 2019-12-16 09:50
 **/

public interface DetailedMapper {
    /**
     * 新增采购明细
     *
     * @return
     */
    @Insert("insert into Detailed values(null,#{orderid},#{storehouseid},#{goodsid},#{number},0,#{price},#{remarks})")
    int addDetailed(@Param("orderid") int orderid, @Param("storehouseid") int storehouseid, @Param("goodsid") int goodsid, @Param("number") int number, @Param("price") int price, @Param("remarks") String remarks);

    /**
     * 查询采购订单的明细
     * @return
     */
    @Select("select Detailed.*,materials.*,depot.* from Detailed,materials,depot  where Detailed.orderid = #{orderid} and detailed.storehouseid=depot.id and detailed.goodsid=materials.id")
    List<HashMap<String,Object>> queryDetailed(@Param("orderid") int orderid);

    /**
     * 修改采购订单的明细
     * @return
     */
    @Update("update Detailed set storehouseid=#{storehouseid},goodsid=#{goodsid},number=#{number},price=#{price},remarks=#{remarks}   where detailed_id = #{id}")
    int updateDetailed(@Param("id") int id, @Param("storehouseid") int storehouseid, @Param("goodsid") int goodsid, @Param("number") int number, @Param("price") int price, @Param("remarks") String remarks);

    /**
     * 删除采购订单的明细
     * @return
     */
    @Delete("delete from Detailed  where detailed_id = #{detailed_id}")
    int deleteDetailed(@Param("detailed_id") int detailed_id);
}
