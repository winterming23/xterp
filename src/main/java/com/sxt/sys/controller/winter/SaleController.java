package com.sxt.sys.controller.winter;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * @param depotItem
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping("/addSale")
    public boolean addSale(Sale sale, Depothead depothead, DepotItem depotItem)throws ParseException{
        return false;
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
}
