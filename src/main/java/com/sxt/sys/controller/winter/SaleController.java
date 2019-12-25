package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.User;
import com.sxt.sys.domain.qxs.warehouse.Depot;
import com.sxt.sys.domain.qxs.warehouse.DepotItem;
import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.vin.Supplier;
import com.sxt.sys.domain.winter.ApplyFor;
import com.sxt.sys.domain.winter.Sale;
import com.sxt.sys.service.qxs.warehouse.DepotHeadServiceI;
import com.sxt.sys.service.qxs.warehouse.DepotItemServiceI;
import com.sxt.sys.service.qxs.warehouse.DepotServiceI;
import com.sxt.sys.service.vin.ProductServiceI;
import com.sxt.sys.service.vin.SupplierServiceI;
import com.sxt.sys.service.winter.ApplyForServiceI;
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
    @Autowired
    private ApplyForServiceI applyForServiceI;

    /**
     * 查询所有销售数据
     * @return
     */
    @RequestMapping("/allSale")
    public String getAllSale(Model model,HttpServletRequest request){
        Integer saleId = (Integer)request.getSession().getAttribute("saleId");
        if (saleId == null){
            saleId = 0;
            List<HashMap> allNoDeleteSale = saleService.getAllNoDeleteSale(saleId);
            model.addAttribute("sale",allNoDeleteSale);
            for (HashMap pro : allNoDeleteSale){
                System.out.println(pro);
            }
        }else {
            List<HashMap> allNoDeleteSale = saleService.getAllNoDeleteSale(saleId);
            model.addAttribute("sale",allNoDeleteSale);
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
     * @param sale 销售数据
     * @param depothead 单据子表数据
     * @param materId 材料编号
     * @param depotItemId 单据子表编号
     * @param saleNumber 销售所需数量
     * @param unitPrice 单价
     * @param depotHeadNumber 单据主表编码
     * @param request
     * @return
     */
    @RequestMapping("/addSale")
    public String addSale(Sale sale, Depothead depothead,
                          int materId,int depotItemId,
                          int saleNumber,double unitPrice,
                          String depotHeadNumber,
                          HttpServletRequest request){
        //获取用户编号用户名称
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getId();
        String userName = user.getName();
        //查询是否存在该单据子表
        DepotItem depotItems = depotItemServiceI.getOneDepotItem(depotItemId);

        //根据产品名称获取产品编号
        String productName = request.getParameter("productName");
        int productId = productServiceI.findProductName(productName);

        //单据子表存在时
        if (depotItems != null) {
            //判断单据中的数量小于需求数量时 生产申请表
            if (depotItems.getBasicNumber() < saleNumber){
                //先添加完销售信息 在获取到新增编号
                //添加销售信息
                sale = new Sale(0,userId,sale.getClientId(),productId,sale.getDepotId(),null,0,saleNumber,sale.getDiscounts(),sale.getMoney(),sale.getReality(),0,sale.getCommission(),0);
                saleService.saveSaleAndDepotHead(sale);
                //存新增销售编号
                request.getSession().setAttribute("saleId",sale.getId());
                //生产数量 在需要销售的基础上 + 10
                int numbers = saleNumber - depotItems.getBasicNumber() + 10;
                ApplyFor applyFor = new ApplyFor(0,"生产计划申请",sale.getProductId(),numbers,"生产申请：缺少产品",0,sale.getUserId(),sale.getId(),0,0);
                applyForServiceI.saveApplyFor(applyFor);
            }else{
                depothead = new Depothead(0,"成品出库",depotHeadNumber,userName,depothead.getCreateTime(),null,null,null,null,unitPrice,sale.getMoney(),null,null,null,0,"0",materId,saleNumber);
                //添加单据主表 它的编号需要获取
                depotHeadServiceI.addDepotHead(depothead);
                //添加销售信息
                sale = new Sale(0,userId,sale.getClientId(),productId,sale.getDepotId(),depothead.getId(),0,saleNumber,sale.getDiscounts(),sale.getMoney(),sale.getReality(),0,sale.getCommission(),0);
                saleService.saveSaleAndDepotHead(sale);
            }
        }
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
