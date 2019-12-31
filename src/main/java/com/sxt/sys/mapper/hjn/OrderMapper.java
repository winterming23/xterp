package com.sxt.sys.mapper.hjn;

import com.sxt.sys.domain.hjn.Orders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: xterp
 * @description: OrderMapper
 * @author: hjn
 * @create: 2019-12-03 16:55
 **/
public interface OrderMapper {
    /**
     * 查询所有采购订单
     *
     * @return
     */
    @Select("select * from Orders")
    List<Orders> queryAllOrders();

    /**
     * 条件查询采购订单
     */
    List<HashMap<String, Object>>  findOrders(String orderid, Date createtime);
    /**
     * 新增采购订单
     *
     * @return
     */
    @Insert("insert into orders values(null,#{orderId},#{supplierid},#{rebate}," +
            "#{paytype},#{total},#{amount_paid},#{total_tax}," +
            "#{orderstate},#{preferential},#{costid},#{createtime},#{purchaserid},0,#{sale_id})")
    int addOrders(Orders order);


    /**
     * 审核订单
     *
     * @return
     */
    //@Update("update Orders set orderstate=#{orderstate} where id=#{id}")
    int updateState(int orderstate, int id);

    /**
     * 转采购入库
     *
     * @return
     */
    //@Update("update Orders set orderstate=3 where id=#{id}")
    int updateOrderState(int id);

    /**
     * 修改采购订单
     *
     * @return
     */
    //@Update("(update Orders set supplierid=#{supplierid},purchaserid=#{purchaserid},rebate=#{rebate},amount_paid=#{amount_paid}   where id=#{id})")
    int updateOrders(int id, int supplierid, int purchaserid, int rebate, int amount_paid);

    /**
     * 查询所有审核状态为 2 的订单费用
     * @return
     */
    @Select("select o.orderid as id,sl.supplier as sName,sl.accountNumber as accoun," +
            " o.purchaserid as pid,o.total as total,o.amount_paid as amount,ct.costtype as costType,ct.costprice as costPrice " +
            " from orders o LEFT JOIN cost ct ON o.costid=ct.id " +
            " LEFT JOIN supplier sl on o.supplierid=sl.id" +
            " where o.orderstate=2 and sl.type='供应商' and sl.delete_Flag=0 and o.finance=0 ")
    List<HashMap> incurExpense();

    /**
     * 修改财务记录添加状态
     * @param id
     * @return
     */
    @Update("update orders set finance=1 where orderid=#{id}")
    boolean updateFinance(Integer id);

    /**
     * 根据订单编号查询
     * @param id
     * @return
     */
    @Select("select * from orders wehre orderid=#{id}")
    Orders queryID(Integer id);
}