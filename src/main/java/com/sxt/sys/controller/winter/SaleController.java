package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.qxs.warehouse.DepotItem;
import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.winter.Sale;
import com.sxt.sys.service.winter.SaleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/17 10:44
 */
@Controller
public class SaleController {
    @Autowired
    private SaleServiceI saleService;

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
     * @param sale
     * @param depothead
     * @param depotItem
     * @return
     * @throws ParseException
     */
    @RequestMapping("/addSale")
    public String saveSaleAndDepotHead(Sale sale, Depothead depothead, DepotItem depotItem) throws ParseException {

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
