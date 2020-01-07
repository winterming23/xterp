package com.sxt.sys.controller.hjn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sxt.sys.domain.hjn.Orders;
import com.sxt.sys.domain.hjn.Payment;
import com.sxt.sys.domain.qxs.warehouse.Depot;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.vin.Supplier;
import com.sxt.sys.service.hjn.CostServicel;
import com.sxt.sys.service.hjn.DetailedServicel;
import com.sxt.sys.service.hjn.OrderServiceI;
import com.sxt.sys.service.qxs.warehouse.DepotServiceI;
import com.sxt.sys.service.vin.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @program: xterp
 * @description: OrderController
+
 * @author: hjn
 * @create: 2019-12-16 10:21
 **/
@Controller
public class OrderController {


    @Autowired
    private OrderServiceI orderServiceI;
    @Autowired
    private CostServicel costServicel;
    @Autowired
    private DetailedServicel detailedServicel;

    @Autowired
    private DepotServiceI depotServiceI;

    @Autowired
    private ProductServiceI productServiceI;


    /**
     * 查询所有未删除的支出与收入记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("queryOrdersPage")
    public String queryOrdersPage(String orderId, String createtime, Model model,int page,int limit) throws ParseException {
        Date date = null;
        if (createtime!= null && createtime !="") {
            /*simpleDateFormat parse = new simpleDateFormat.parse(createtime);*/
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            date = format.parse(createtime);
        }

        System.out.println(orderId);
        System.out.println(createtime);
        System.out.println(page+"当前页数");
        System.out.println(limit+"--------每页显示条数");
        int ii=(page-1)*limit;

        //查询采购订单
        List<HashMap<String, Object>> listOrders = orderServiceI.findOrders(orderId, date,ii,limit);
        int listOrdersCount = orderServiceI.findOrdersCount(orderId, date);
        model.addAttribute("listOrders", listOrders);
        for (HashMap h : listOrders) {
            System.out.println(h);
            if ((int) h.get("orderstate") == 1) {
                h.put("orderstatename", "未审核");
            } else if ((int) h.get("orderstate") == 2) {
                h.put("orderstatename", "已审核");
            } else if ((int) h.get("orderstate") == 3) {
                h.put("orderstatename", "已入库");
            }else if ((int) h.get("orderstate") == 4) {
                h.put("orderstatename", "待仓库审核");
            }
        }


        String s = JSON.toJSONString(listOrders);

        JSONObject obj = new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count",listOrdersCount);
        obj.put("data", listOrders);
                return obj.toJSONString();
    }

    @RequestMapping("queryPage")
    public String queryPage() {
        //查询采购订单
        return "system/hjn/orderShow";
    }

    @ResponseBody
    @RequestMapping("querySupplier")
    public String querySupplier() {
        //查询供应商
        List<Supplier> listSuppliers = orderServiceI.allSupplier();
        String s = JSON.toJSONString(listSuppliers);
        System.out.println(listSuppliers);
        JSONObject obj = new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", listSuppliers.size());
        obj.put("data", listSuppliers);
        return obj.toJSONString();
    }


    //审核订单
    @ResponseBody
    @RequestMapping("shenhe")
    public String shenhe(String orderstate, String orderid) {
        int id = Integer.parseInt(orderid);
        int orderstatea = Integer.parseInt(orderstate);
        orderServiceI.updateState(orderstatea, id);
        return null;
    }

    //删除明细
    @ResponseBody
    @RequestMapping("shanchumingxi")
    public String shanchumingxi(int detailed_id) {
        detailedServicel.deleteDetailed(detailed_id);
        System.out.println("哈哈哈哈");

        return null;
    }

    //删除订单
    @ResponseBody
    @RequestMapping("shanchudingdan")
    public String shanchudingdan(int orderid) {

        System.out.println("删除订单");
        orderServiceI.deleteOrder(orderid);
        return null;
    }

    //删除明细
    @ResponseBody
    @RequestMapping("tuihuo")
    public String tuihuo(int orderid) {

        System.out.println("退货");

        return null;
    }

    @ResponseBody
    @RequestMapping("findAllDepot")
    public List findAllDepot() throws IOException {
        System.out.println("仓库查询");
        List<Depot> listDepot = depotServiceI.queryNotDeleteDepot();
      //  String s = JSON.toJSONString(listDepot);
       // PrintWriter out= response.getWriter();
        System.out.println(listDepot.size()+"----------------->");
        //JSONObject obj = new JSONObject();
        //前台通过key值获得对应的value值
        //obj.put("code", 0);
        //obj.put("msg", "");
        //obj.put("count", listDepot.size());
       // obj.put("data", listDepot);
        //out.print(listDepot);
        return listDepot;
    }

    @ResponseBody
    @RequestMapping("selectMaterials")
    public List selectMaterials() throws IOException {

        List<Materials> materials = orderServiceI.selectMaterials();
        System.out.println(materials.size()+"----------------->查询所有零件");
        return materials;
    }

    @ResponseBody
    @RequestMapping("findSupplier")
    public List findSupplier() throws IOException {
        System.out.println("供应商查询");
        List<Supplier> suppliers = orderServiceI.allSupplier();
        //  String s = JSON.toJSONString(listDepot);
        // PrintWriter out= response.getWriter();
        System.out.println(suppliers.size()+"----------------->");
        //JSONObject obj = new JSONObject();
        //前台通过key值获得对应的value值
        //obj.put("code", 0);
        //obj.put("msg", "");
        //obj.put("count", listDepot.size());
        // obj.put("data", listDepot);
        //out.print(listDepot);
        return suppliers;
    }


    //加载付款方式下拉框
    @ResponseBody
    @RequestMapping("findPayment")
    public List findPayment() throws IOException {
        System.out.println("付款方式查询");
        List<Payment> payments = orderServiceI.allPayment();
        System.out.println(payments.size()+"----------------->");
        return payments;
    }

    @ResponseBody
    @RequestMapping("findAllProduct")
    public String findAllProduct() {
        List<HashMap> allProduct = productServiceI.getAllProduct();
        String s = JSON.toJSONString(allProduct);
        System.out.println(allProduct);
        JSONObject obj = new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", allProduct.size());
        obj.put("data", allProduct);
        return obj.toJSONString();
    }

    @ResponseBody
    @RequestMapping("queryDetailed")
    public String queryDetailed(String orderid) {
        System.out.println(orderid);
        List<HashMap<String, Object>> hashMaps = detailedServicel.queryDetailed(Integer.parseInt(orderid));

        String s = JSON.toJSONString(hashMaps);
        System.out.println(hashMaps +"----------------------------采购明细");
        JSONObject obj = new JSONObject();
        //前台通过key值获得对应的value值
        obj.put("code", 0);
        obj.put("msg", "");
        obj.put("count", hashMaps.size());
        obj.put("data", hashMaps);
        return obj.toJSONString();
    }

    @RequestMapping("mingxiShow")
    public String mingxiShow(int orderId, Model model){

        String s= orderId+"";

        List<HashMap<String, Object>> orders = orderServiceI.findOrders(s, null,0,5 );
        System.out.println(orders+"-------------------------------明细mingxiShow()");
        model.addAttribute("order",orders.get(0));
        model.addAttribute("orderId",orderId);
        return "system/hjn/detailedShow";
    }

    @ResponseBody
    @RequestMapping("mingxiS")
    public String mingxiS(){
     //   orderServiceI.addOrders();
        orderServiceI.getOrders();
        return null;
    }



    @RequestMapping("xiangqing")
    public String xiangqing(int orderId, Model model){
        String s= orderId+"";
        List<HashMap<String, Object>> orders = orderServiceI.findOrders(s, null,0,5);
        System.out.println(orders+"-------------------------------详情xiangqing()");
        model.addAttribute("order",orders.get(0));
        model.addAttribute("orderId",orderId);
        return "system/hjn/xiangqingShow";
    }



    @RequestMapping("addOrUpdate")
    public String addOrUpdate(Orders orders, String id, HttpServletResponse response, int alltotal) throws IOException {
        System.out.println(id);
        System.out.println(orders.getAmount_paid());
        System.out.println("添加修改的方法");
        if (id==null && id==""){
           int i= orderServiceI.addOrders(orders);
        }else{
            int i= orderServiceI.updateOrders(Integer.parseInt(id),orders.getPreferential(),orders.getSupplierid(),1,orders.getRebate(),orders.getAmount_paid());
            if (orders.getOrderstate()==2){
                Date date = new Date();
                List<HashMap<String, Object>> hashMaps = detailedServicel.queryDetailed(Integer.parseInt(id));
                for (HashMap h : hashMaps) {
                    System.out.println(id +"循环添加入库记录"+h.get("goodsid")+h.get("number"));
                    System.out.println(orders);
                    System.out.println(alltotal);
                    //循环添加入库记录
                    date = new Date();
                    int add = orderServiceI.add(Integer.parseInt(id), date, orders.getSupplierid(), orders.getAmount_paid(), alltotal, orders.getPaytype(), Integer.parseInt(h.get("goodsid").toString()), Integer.parseInt(h.get("number").toString()));
                }
                int a= orderServiceI.updateState(4,Integer.parseInt(id));
            }
        }

        return "system/hjn/orderShow";
    }

   // 刷新总价格
    @ResponseBody
    @RequestMapping("selectTatol")
    public int selectTatol(int orderId) throws IOException {
        System.out.println("刷新总价格");
        // int tatol
        int tatol = orderServiceI.getTatol(orderId);
        System.out.println(tatol+"----------------->");
        return tatol;
    }



   //修改明细
   @ResponseBody
   @RequestMapping("updateDetailed")
   public String updateDetailed(int detailed_id,int name,int product_name,int number,int price,String remarks) {
      // int id = Integer.parseInt(detailed_id);
       //int orderstatea = Integer.parseInt(orderstate);
       System.out.println("修改明细的方法------------------------>");
        int i=detailedServicel.updateDetailed(detailed_id,name,product_name,number,price,remarks);
       return null;
   }

//添加明细
    @ResponseBody
    @RequestMapping("addDetailed")
    public String addDetailed(int detailed_id,int name,int product_name,int number,int price,String remarks) {
        // int id = Integer.parseInt(detailed_id);
        //int orderstatea = Integer.parseInt(orderstate);
        System.out.println("添加明细的方法------------------------>");
        int i=detailedServicel.addDetailed(detailed_id,name,product_name,number,price,remarks);
        return null;
    }


}