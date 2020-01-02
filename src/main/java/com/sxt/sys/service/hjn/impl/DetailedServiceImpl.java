package com.sxt.sys.service.hjn.impl;

import com.sxt.sys.mapper.hjn.DetailedMapper;
import com.sxt.sys.service.hjn.DetailedServicel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
    public int addDetailed(int id,int storehouseid,int goodsid,int number,int price,String remarks) {
        return detailedMapper.addDetailed( id, storehouseid, goodsid, number, price, remarks);
    }

    @Override
    public List<HashMap<String,Object>> queryDetailed(int orderid) {
        return detailedMapper.queryDetailed(orderid);
    }

    /**
     * 查询采购订单的明细
     * @return
     */
//    @Override
//    public List<Detailed> queryDetailed(int orderid) {
//        return detailedMapper.queryDetailed(orderid);
//    }

    /**
     * 修改采购订单的明细
     * @return
     */
    @Override
    public int updateDetailed(int id,int storehouseid,int goodsid,int number,int price,String remarks) {
        return detailedMapper.updateDetailed(id, storehouseid, goodsid, number, price, remarks);
    }

    /**
     * 删除采购订单的明细
     * @return
     */
    @Override
    public int deleteDetailed(int id) {
        return detailedMapper.deleteDetailed(id);
    }
}
