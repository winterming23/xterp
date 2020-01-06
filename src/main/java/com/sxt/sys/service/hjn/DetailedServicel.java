package com.sxt.sys.service.hjn;

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
    int addDetailed(int id, int storehouseid, int goodsid, int number, int price, String remarks);

    /**
     * 查询采购订单的明细
     * @return
     */
    List<HashMap<String,Object>> queryDetailed(int orderid);

    /**
     * 修改采购订单的明细
     * @return
     */
    int updateDetailed(int id, int storehouseid, int goodsid, int number, int price, String remarks);

    /**
     * 删除采购订单的明细
     * @return
     */
    int deleteDetailed(int id);
}
