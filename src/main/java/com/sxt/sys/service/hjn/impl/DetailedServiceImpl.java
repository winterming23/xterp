package com.sxt.sys.service.hjn.impl;

import com.sxt.sys.domain.hjn.Detailed;
import com.sxt.sys.mapper.hjn.DetailedMapper;
import com.sxt.sys.service.hjn.DetailedServicel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: xterp
 * @description: DetailedServiceImpl
 * @author: hjn
 * @create: 2019-12-16 10:01
 **/
@Service
public class DetailedServiceImpl implements DetailedServicel {
    @Autowired
    private DetailedMapper detailedMapper;

    public DetailedMapper getDetailedMapper() {
        return detailedMapper;
    }
    /**
     * 新增采购明细
     *
     * @return
     */
    @Override
    public int addDetailed(Detailed detailed) {
        return detailedMapper.addDetailed(detailed);
    }

    /**
     * 查询采购订单的明细
     * @return
     */
    @Override
    public List<Detailed> queryDetailed(int orderid) {
        return detailedMapper.queryDetailed(orderid);
    }

    /**
     * 修改采购订单的明细
     * @return
     */
    @Override
    public int updateDetailed(Detailed detailed) {
        return detailedMapper.updateDetailed(detailed);
    }

    /**
     * 删除采购订单的明细
     * @return
     */
    @Override
    public int updateDetailed(int id) {
        return detailedMapper.updateDetailed(id);
    }
}
