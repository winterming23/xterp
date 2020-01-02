package com.sxt.sys.mapper.hjn;

import com.sxt.sys.domain.hjn.Orders;
import com.sxt.sys.domain.hjn.Payment;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.vin.Product;
import com.sxt.sys.domain.vin.Supplier;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
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
    List<HashMap<String, Object>>  findOrders(@Param("orderid") String orderid, @Param("createtime") Date createtime);

    /**
     * 条件查询采购订单
     */

    /**
     * 新增采购订单
     *
     * @return
     */
    //@Insert("insert into orders values(null,#{orderid},#{supplierid},#{purchaserid},#{rebate},#{paytype},#{total},#{amount_paid},#{total_tax},#{orderstate},#{preferential},#{costid})")
    int addOrders(Orders order);


    /**
     * 审核订单
     *
     * @return
     */
    @Update("update Orders set orderstate=#{orderstate} where id=#{id}")
    int updateState(@Param("orderstate") int orderstate, @Param("id") int id);

    /**
     * 转采购入库
     * @return
     */
    //@Update("update Orders set orderstate=3 where id=#{id}")
    int updateOrderState(int id);

    /**
     * 修改采购订单
     *
     * @return
     */
    @Update("update Orders" +
            " set preferential=#{preferential}," +
            "supplierid=#{supplierid}," +
            "purchaserid=#{purchaserid}," +
            "rebate=#{rebate}," +
            "orders.amount_paid=#{amount_paid} " +
            "  where orderId=#{id}")
    int updateOrders(@Param("id") int id, @Param("preferential") int preferential, @Param("supplierid") int supplierid, @Param("purchaserid") int purchaserid, @Param("rebate") int rebate, @Param("amount_paid") int amount_paid);




    //查询所有供应商
    @Select("select * from Supplier")
    List<Supplier> allSupplier();
    //查询所有商品
    @Select("select * from product")
    List<Product> allproduct();

    //查询所有付款方式
    @Select("select * from payment")
    List<Payment> allpayment();


    //查询所有商品方式
    @Select("select * from Materials")
    List<Materials> selectMaterials();

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
    @Select("select * from orders where orderid=#{id}")
    Orders queryID(Integer id);

    /**
     * 查询最大订单orderid
     * @param
     * @return
     */
    @Select("select max(orderId)+1 from orders")
    int queryMaxID();


    /**
     * 生成订单记录
     * @param
     * @return
     */
    @Insert("insert into orders values(null ,#{orderid},1,0,1,0,0,0,1,0,0,#{date},1,0,0)")
    int queryMaxId(@Param("orderid") int orderid, @Param("date") Date date);


    /**
     * 添加仓库入库记录
     * @param
     * @return
     */
    @Insert("insert into depothead values(null,'零件入库',null,null,#{createtime},null,#{supplierid},1,1,#{amount_paid},#{totalPrice},#{paytype},null,0,0,0,#{goodsid},#{number})")
    int add(@Param("orderid") int orderid, @Param("createtime") Date createtime, @Param("supplierid") int supplierid, @Param("amount_paid") int amount_paid, @Param("totalPrice") int totalPrice, @Param("paytype") int paytype, @Param("goodsid") int goodsid, @Param("number") int number);

    /**
     * 根据订单编号查询总价格
     * @param orderid
     * @return
     */
    @Select("select sum(price*number) from detailed where orderid=#{orderid} group by orderid")
    int  getTatol(@Param("orderid") Integer orderid);

}