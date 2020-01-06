package com.sxt.sys.service.hjn.impl;

import com.sxt.sys.domain.hjn.Orders;
import com.sxt.sys.domain.hjn.Payment;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.vin.Supplier;
import com.sxt.sys.mapper.hjn.OrderMapper;
import com.sxt.sys.service.hjn.OrderServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: xterp
 * @description: OrderServiceImpl
 * @author: hjn
 * @create: 2019-12-16 10:00
 **/
@Service
public class OrderServiceImpl implements OrderServiceI {
    @Autowired
    private OrderMapper orderMapper;

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    /**
     * 查询所有采购订单
     * @return
     */
    @Override
    public List<HashMap<String, Object>>  findOrders(String orderid, Date createtime,int pages,int pageCount) {
        List<HashMap<String, Object>>  list=orderMapper.findOrders(orderid,createtime ,pages,pageCount);
        return list;
    }

    @Override
    public int findOrdersCount(String orderid, Date createtime) {
        return orderMapper.findOrdersCount(orderid,createtime );
    }

    /**
     * 添加采购订单
     * @return
     */
    @Override
    public int addOrders(Orders order) {
        return orderMapper.addOrders(order);
    }

    /**
     * 审核采购
     * @return
     */
    @Override
    public int updateState(int orderstate, int id) {
        return orderMapper.updateState(orderstate,id);
    }

    /**
     * 修改采购订单
     * @param id
     * @param supplierid
     * @param purchaserid
     * @param rebate
     * @param amount_paid
     * @return
     */
    @Override
    public int updateOrders(int id, int preferential,int supplierid, int purchaserid, int rebate, int amount_paid) {
        return orderMapper.updateOrders(id,preferential,supplierid,purchaserid,rebate,amount_paid);
    }

    @Override
    public List<Supplier> allSupplier() {
        return orderMapper.allSupplier();
    }

    @Override
    public List<Payment> allPayment() {
        return orderMapper.allpayment();
    }

    @Override
    public List<Materials> selectMaterials() {
        return orderMapper.selectMaterials();
    }

    @Override
    public int getTatol(int orderid) {
        return orderMapper.getTatol(orderid);
    }

    @Override
    public int getOrders() {
        return orderMapper.queryMaxId(orderMapper.queryMaxID(),new Date());
    }

    @Override
    public int add(int orderid, Date createtime, int supplierid, int amount_paid, int totalPrice, int paytype, int goodsid, int number) {
        return orderMapper.add(orderid,createtime,supplierid,amount_paid,totalPrice,paytype,goodsid,number);
    }

    @Override
    public int deleteOrder(int orderid) {
        return orderMapper.deleteOrder(orderid);
    }

}
