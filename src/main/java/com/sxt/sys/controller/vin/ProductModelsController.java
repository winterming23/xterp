package com.sxt.sys.controller.vin;

import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.vin.Product_type;
import com.sxt.sys.service.vin.ProductModelsServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("productModels/")
public class ProductModelsController {

    @Resource
    private ProductModelsServiceI productModelsServiceI;

    /**
     * 获取所有产品信息
     * @param request
     * @return
     */
    @RequestMapping("getAllProductModels")
    public String getAllProductModels(HttpServletRequest request){
        List<HashMap> productModels = productModelsServiceI.getAllProductModels();
        request.setAttribute("productModels",productModels);
        for (HashMap pro : productModels){
            System.out.println(pro);
        }
        return  "system/vin/productModels";
    }

    /**
     * 新增产品信息
     * @param product_model
     * @return
     */
    @RequestMapping("insertProductModels")
    @ResponseBody
    public String insertProductModels(Product_model product_model){
        System.out.println("============================================================================================");
        System.out.println(product_model);
        boolean insertProductModels = productModelsServiceI.insertProductModels(product_model);
        if (insertProductModels){
            return "insertProductModels";
        }else {
            return "";
        }
    }

    /**
     * 删除产品信息（修改删除标记）
     * @param id
     * @return
     */
    @RequestMapping("deleteProductModels")
    @ResponseBody
    public String deleteProductModels(Long id){
        System.out.println("============================================================================================");
        System.out.println(id);
        boolean deleteProduct = productModelsServiceI.deleteProductModels(id);
        if(deleteProduct){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 修改产品信息
     * @param product_model
     * @return
     */
    @RequestMapping("updateProductModels")
    @ResponseBody
    public String updateProductModels(Product_model product_model){
        System.out.println(product_model);
        product_model.setCreatetime(new Date());
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println(product_model);
        boolean updateProduct = productModelsServiceI.updateProductModels(product_model);
        if (updateProduct){
            return "true";
        }else {
            return "";
        }
    }

    @RequestMapping("queryType")
    public String queryType(Model model){
        List<Product_type> product_typess = productModelsServiceI.queryType();
        model.addAttribute("product_typess",product_typess);
        for (Product_type list : product_typess){
            System.out.println(list);
        }
        return "system/vin/insertProductModels";
    }

    @RequestMapping("deleteProductModelss")
    @ResponseBody
    public String deleteAll(Long[] proids){
        boolean bool = false;
        for (int i=0;i<proids.length;i++){
            System.out.println(proids[i]);
            bool =   productModelsServiceI.deleteProductModels(proids[i]);
        }
        if (bool){
            return "true";
        }else {
            return "";
        }
    }

    @RequestMapping("updateProducrtModelsStatus")
    @ResponseBody
    public String updateProducrtModelsStatus(long proStaid){
        System.out.println(proStaid);
        boolean flag = productModelsServiceI.updateProductModelsStatus(proStaid);
        if (flag){
            return "system/vin/productModels";
        }else {
            return "";
        }
    }

    @RequestMapping("queryByModelId")
    public String queryByModelId(Long id, Model model){
        System.out.println(id);
        Product_model productModel = productModelsServiceI.queryByModelId(id);
        List<Product_type> productTypes = productModelsServiceI.queryType();
        model.addAttribute("productTypes",productTypes);
        model.addAttribute("productModel",productModel);
        return "system/vin/updateProductModels";
    }
}
