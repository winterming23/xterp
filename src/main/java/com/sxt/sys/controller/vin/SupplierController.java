package com.sxt.sys.controller.vin;


import com.sxt.sys.domain.vin.Supplier;
import com.sxt.sys.service.vin.SupplierServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("supplier/")
public class SupplierController {

    @Resource
    private SupplierServiceI supplierServiceIl;

    /**
     * 获取所有供应商信息
     * @param model
     * @return
     */
    @RequestMapping("getAllSupplierG")
    public String getAllSupplierG(Model model){
        List<Supplier> suppliers = supplierServiceIl.getAllSupplierG();
        model.addAttribute("suppliers",suppliers);
        for (Supplier supplier : suppliers){
           System.out.println(supplier);
        }
        return  "system/vin/supplier/supplierG";
    }

    /**
     * 获取所有供应商信息
     * @param model
     * @return
     */
    @RequestMapping("getAllSupplierK")
    public String getAllSupplierK(Model model){
        List<Supplier> suppliers = supplierServiceIl.getAllSupplierK();
        model.addAttribute("suppliers",suppliers);
        for (Supplier supplier : suppliers){
            System.out.println(supplier);
        }
        return  "system/vin/supplier/supplierK";
    }

    /**
     * 删除供应商信息（修改删除标记）
     * @param id
     * @return
     */
    @RequestMapping("deleteSupplierG")
    @ResponseBody
    public String deleteSupplierG(Long id){
        System.out.println("============================================================================================");
        System.out.println(id);
        boolean deleteProduct = supplierServiceIl.deleteSupplier(id);
        if(deleteProduct){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 删除客户信息（修改删除标记）
     * @param id
     * @return
     */
    @RequestMapping("deleteSupplierK")
    @ResponseBody
    public String deleteSupplierK(Long id){
        System.out.println("============================================================================================");
        System.out.println(id);
        boolean deleteProduct = supplierServiceIl.deleteSupplier(id);
        if(deleteProduct){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 批量删除供应商信息
     * @param supid
     * @return
     */
    @RequestMapping("deleteSuppliersG")
    @ResponseBody
    public String deleteSuppliersG(Long[] supid){
        boolean bool = false;
        for (int i=0;i<supid.length;i++){
            bool =   supplierServiceIl.deleteSupplier(supid[i]);
        }
        if (bool){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 批量删除客户信息
     * @param supid
     * @return
     */
    @RequestMapping("deleteSuppliersK")
    @ResponseBody
    public String deleteSuppliersK(Long[] supid){
        boolean bool = false;
        for (int i=0;i<supid.length;i++){
            bool =   supplierServiceIl.deleteSupplier(supid[i]);
        }
        if (bool){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 根据id获取供应商信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("queryBySupplierGId")
    public String queryBySupplierGId(Long id, Model model){
        Supplier supplierG = supplierServiceIl.queryBySupplierId(id);
        System.out.println(supplierG);
        model.addAttribute("supplierG",supplierG);
        return "system/vin/supplier/updateSupplierG";
    }

    /**
     * 根据id获取客户信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("queryBySupplierKId")
    public String queryBySupplierKId(Long id, Model model){
        Supplier supplierK = supplierServiceIl.queryBySupplierId(id);
        model.addAttribute("supplierK",supplierK);
        System.out.println(supplierK);
        return "system/vin/supplier/updateSupplierK";
    }

    /**
     * 修改供应商状态为启用
     * @param supid
     * @return
     */
    @RequestMapping("updateSupplierStateT")
    @ResponseBody
    public String updateSupplierStateT(Long[] supid){
        boolean bool = false;
        for (int i=0;i<supid.length;i++){
            System.out.println(supid[i]);
            bool = supplierServiceIl.updateSupplierEnabledT(supid[i]);
        }
        if (bool){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 修改状态为禁用
     * @param supid
     * @return
     */
    @RequestMapping("updateSupplierStateF")
    @ResponseBody
    public String updateSupplierStateF(Long[] supid){
        boolean bool = false;
        for (int i=0;i<supid.length;i++){
            bool =   supplierServiceIl.updateSupplierEnabledF(supid[i]);
        }
        if (bool){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 修改审核状态
     * @param supStaid
     * @return
     */
    @RequestMapping("updateSupplierStatus")
    @ResponseBody
    public String updateSupplierStatus(long supStaid){
        System.out.println(supStaid);
        boolean flag = supplierServiceIl.updateSupplierStatus(supStaid);
        if (flag){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 修改客户/供应商信息
     * @param supplier
     * @return
     */
    @RequestMapping("updateSupplier")
    @ResponseBody
    public String updateProduct(Supplier supplier){
        System.out.println(supplier);
        boolean updateSupplier = supplierServiceIl.updateSupplier(supplier);
        if (updateSupplier){
            return "true";
        }else {
            return "";
        }
    }

    @RequestMapping("insertG")
    public String insertG(){
        return "system/vin/supplier/insertSupplierG";
    }

    @RequestMapping("insertK")
    public String insertK(){
        return "system/vin/supplier/insertSupplierK";
    }

    /**
     * 添加供应商信息
     * @param supplier
     * @return
     */
    @RequestMapping("insertSupplierG")
    @ResponseBody
    public String insertSupplierG(Supplier supplier){
        System.out.println(supplier);
        boolean flag = supplierServiceIl.insertSupplierG(supplier);
        if (flag){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 添加客户信息
     * @param supplier
     * @return
     */
    @RequestMapping("insertSupplierK")
    @ResponseBody
    public String insertSupplierK(Supplier supplier){
        System.out.println(supplier);
        boolean flag = supplierServiceIl.insertSupplierK(supplier);
        if (flag){
            return "true";
        }else {
            return "";
        }
    }
}
