package com.sxt.sys.service.hjn;

import com.sxt.sys.domain.hjn.Detailed;

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
    int addDetailed(Detailed detailed);

    /**
     * 查询采购订单的明细
     * @return
     */
    List<Detailed> queryDetailed(int orderid);

    /**
     * 修改采购订单的明细
     * @return
     */
    int updateDetailed(Detailed detailed);

    /**
     * 删除采购订单的明细
     * @return
     */
    int updateDetailed(int id);
}
