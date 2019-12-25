package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.User;
import com.sxt.sys.domain.qxs.warehouse.Depot;
import com.sxt.sys.domain.qxs.warehouse.DepotItem;
import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.vin.Supplier;
import com.sxt.sys.domain.winter.Sale;
import com.sxt.sys.service.qxs.warehouse.DepotHeadServiceI;
import com.sxt.sys.service.qxs.warehouse.DepotItemServiceI;
import com.sxt.sys.service.qxs.warehouse.DepotServiceI;
import com.sxt.sys.service.vin.ProductServiceI;
import com.sxt.sys.service.vin.SupplierServiceI;
import com.sxt.sys.service.winter.SaleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Winter
 * @Date 2019/12/17 10:44
 */
@Controller
public class SaleController {
    @Autowired
    private SaleServiceI saleService;
    @Autowired
    private DepotHeadServiceI depotHeadServiceI;
    @Autowired
    private DepotItemServiceI depotItemServiceI;
    @Autowired
    private SupplierServiceI supplierServiceI;
    @Autowired
    private DepotServiceI depotServiceI;
    @Autowired
    private ProductServiceI productServiceI;

    /**
     * 查询所有销售数据
     * @return
     */
    @RequestMapping("/allSale")
    public String getAllSale(Model model){
        List<HashMap> allNoDeleteSale = saleService.getAllNoDeleteSale();
        model.addAttribute("sale",allNoDeleteSale);
        for (HashMap pro : allNoDeleteSale){
            System.out.println(pro);
        }
        return "system/winter/sale/allSale";
    }

    /**
     * @param model 前往添加页面
     * @return
     * @throws ParseException
     */
    @RequestMapping("/toSale")
    public String saveSaleAndDepotHead(Model model) {
        //查询所有客户
        List<Supplier> suppliers = supplierServiceI.getAllSupplierK();
        //查询所有的仓库
        List<Depot> depots = depotServiceI.queryNotDeleteDepot();
        //查询所有产品
        List<HashMap> products = productServiceI.getAllProduct();
        model.addAttribute("supplier",suppliers);
        model.addAttribute("depot",depots);
        model.addAttribute("product",products);
        return "system/winter/sale/saveSale";
    }

    /**
     * 通过产品编号查询库存
     * @param depotId
     * @param designation
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectItem")
    public Map selectItem(String designation, Integer depotId){
        System.err.println("select Item");
        HashMap map = depotItemServiceI.getCount(designation, depotId);
        return map;
    }


    /**
     * 新增数据
     * @param sale
     * @param depothead
     * @param depotItemId
     * @return
     * @throws ParseException
     */
    @RequestMapping("/addSale")
    public String addSale(Sale sale, Depothead depothead, int materId,HttpServletRequest request)throws ParseException{
        //获取用户编号用户名称
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        String userName = user.getName();
        String saleNumber = request.getParameter("saleNumber");
        int number = Integer.parseInt(saleNumber);
        String unitPrice = request.getParameter("unitPrice");
        double price = Double.parseDouble(unitPrice);
        String depotHeadNumber = request.getParameter("depotHeadNumber");
        depothead = new Depothead(0,"成品出库",depotHeadNumber,userName,depothead.getCreateTime(),null,null,null,null,price,sale.getMoney(),null,null,null,0,"0",materId,number);
        //先添加单据主表 它的编号需要获取
        depotHeadServiceI.addDepotHead(depothead);
        //根据产品名称获取产品编号
        String productName = request.getParameter("productName");
        int productId = productServiceI.findProductName(productName);
        sale = new Sale(0,userId,sale.getClientId(),productId,sale.getDepotId(),depothead.getId(),0,number,sale.getDiscounts(),sale.getMoney(),sale.getReality(),0,sale.getCommission(),0);
        boolean flag = saleService.saveSaleAndDepotHead(sale);
        return "system/winter/sale/saveSale";
    }

    /**
     * 删除销售信息
     * @return
     */
    @RequestMapping("/deleteSale")
    @ResponseBody
    public boolean deleteSale(int deleteFlag,int id){
        boolean flag = saleService.deleteSale(deleteFlag, id);
        return flag;
    }

    /**
     * 修改状态
     * @param state
     * @param id
     * @return
     */
    @RequestMapping("/updateSaleState")
    @ResponseBody
    public boolean updateSaleState(int state,int id){
        boolean flag = saleService.updateSaleState(state,id);
        return flag;
    }

    /**
     * 修改销售信息
     * @param number
     * @param id
     * @return
     */
    @RequestMapping("/updateSaleNumber")
    @ResponseBody
    public boolean updateSale(@RequestParam("number") Integer number, @RequestParam("id") Integer id){
        boolean flag = saleService.updateSaleNumber(number,id);
        return flag;
    }

    /**
     * 查询单条销售信息
     * @return
     */
    @RequestMapping("/findSale")
    public String findSale(int id,Model model){
        HashMap saleOne = saleService.findSaleOne(id);
        model.addAttribute("saleOne",saleOne);
        return "system/winter/sale/updateSale";
    }
}
