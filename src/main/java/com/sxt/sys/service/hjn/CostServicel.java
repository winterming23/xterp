package com.sxt.sys.service.hjn;

import com.sxt.sys.domain.hjn.Cost;

import java.util.List;

/**
 * @program: xterp
 * @description: CostServicel
 * @author: hjn
 * @create: 2019-12-16 09:45
 **/
public interface CostServicel {
    /**
     * 添加采购支出
     * @param cost
     * @return
     */
    int addCost(Cost cost);

    /**
     * 删除采购支出
     */
    int delectCost(int id);

    /**
     * 查采购支出
     *
     * @return
     */
    List<Cost> findCost(int orderid);

    /**
     * 修改采购支出
     *
     * @return
     */
    int delectCost(int id, String costtype, int costprice);
}
