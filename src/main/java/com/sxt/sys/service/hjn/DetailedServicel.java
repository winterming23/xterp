package com.sxt.sys.service.hjn;

import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @program: xterp
 * @description: DetailedServicel
 * @author: hjn
 * @create: 2019-12-16 09:59
 **/
public interface DetailedServicel {
    /**
     * 新增采购明细
     *
     * @return
     */
    int addDetailed(@Param("orderid") int orderid, @Param("storehouseid") int storehouseid, @Param("goodsid") int goodsid, @Param("number") int number, @Param("price") int price, @Param("remarks") String remarks);

    /**
     * 查询采购订单的明细
     * @return
     */
    List<HashMap<String,Object>> queryDetailed(int orderid);

    /**
     * 修改采购订单的明细
     * @return
     */
    int updateDetailed(@Param("id") int id, @Param("storehouseid") int storehouseid, @Param("goodsid") int goodsid, @Param("number") int number, @Param("price") int price, @Param("remarks") String remarks);

    /**
     * 删除采购订单的明细
     * @return
     */
    int deleteDetailed(int id);
}
