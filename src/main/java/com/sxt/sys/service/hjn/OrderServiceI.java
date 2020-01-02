package com.sxt.sys.service.hjn;

import com.sxt.sys.domain.hjn.Orders;
import com.sxt.sys.domain.hjn.Payment;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.vin.Supplier;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: xterp
 * @description: OrderServiceI
 * @author: hjn
 * @create: 2019-12-16 08:56
 **/
public interface OrderServiceI {
    /**
     * 查询所有采购订单
     * @return
     */
    List<HashMap<String, Object>> findOrders(@Param("orderid") String orderid, @Param("createtime") Date createtime);

    /**
     * 添加采购订单
     * @return
     */
    int addOrders(Orders order);

    /**
     * 审核采购
     * @return
     */
    int updateState(int orderstate, int id);

    /**
     * 修改采购订单
     * @param id
     * @param supplierid
     * @param purchaserid
     * @param rebate
     * @param amount_paid
     * @return
     */
    int updateOrders(@Param("id") int id, @Param("preferential") int preferential, @Param("supplierid") int supplierid, @Param("purchaserid") int purchaserid, @Param("rebate") int rebate, @Param("amount_paid") int amount_paid);

    //查询所有供应商
    List<Supplier> allSupplier();


    //查询所有付款方式
    List<Payment> allPayment();

    //查询所有零件
    List<Materials> selectMaterials();

    //查询总价格
    int getTatol(int orderid);
    //生产一条订单
    int getOrders();

    //添加明细到仓库入库记录
    int add(@Param("orderid") int orderid, @Param("createtime") Date createtime, @Param("supplierid") int supplierid, @Param("amount_paid") int amount_paid, @Param("totalPrice") int totalPrice, @Param("paytype") int paytype, @Param("goodsid") int goodsid, @Param("number") int number);
}
