package com.sxt.sys.controller.vin;

import com.sxt.sys.domain.qxs.warehouse.DepotItem;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.vin.Product;
import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.vin.Product_type;
import com.sxt.sys.service.qxs.warehouse.DepotItemServiceI;
import com.sxt.sys.service.qxs.warehouse.MaterialsServiceI;
import com.sxt.sys.service.vin.ProductServiceI;
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
@RequestMapping("product/")
public class ProductController {
    @Resource
    private ProductServiceI productServiceI;
    @Resource
    private MaterialsServiceI materialsServiceI;
    @Resource
    private DepotItemServiceI depotItemServiceI;
    /**
     * 获取所有产品信息
     * @param request
     * @return
     */
    @RequestMapping("getAllProduct")
    public String getAllProduct(HttpServletRequest request){
        List<HashMap> listproducthash = productServiceI.getAllProduct();
        request.setAttribute("listproducthash",listproducthash);
        for (HashMap pro : listproducthash){
            System.out.println(pro);
        }
        return  "system/vin/product";
    }

    /**
     * 获取所有产品型号信息
     * @param request
     * @return
     */
    @RequestMapping("getProductModel")
    public String getProductModel(HttpServletRequest request){
        List<Product_model> product_models = productServiceI.getProductModel();
        request.setAttribute("productModel",product_models);
        for (Product_model models : product_models){
            System.out.println(models);
        }
        return "system/vin/product";
    }

    /**
     * 根据id获取产品产品信息
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("getProductById")
    public String getProductById(long id, Model model){
        List<Product_model> product_models = productServiceI.getProductModel();
        Product product = productServiceI.getProductById(id);
        model.addAttribute("product_models",product_models);
        model.addAttribute("product",product);
        if (product!=null){
            System.out.println(product);
        }
        return "system/vin/updateProduct";
    }

    /**
     * 获取产品型号信息
     * @param product
     * @param model
     * @return
     */
    @RequestMapping("insert_Product")
    public String insertProduct(Product product, Model model){
        List<Product_model> product_models = productServiceI.getProductModel();
        model.addAttribute("product_models",product_models);
        for (Product_model list : product_models){
            System.out.println(list);
        }
        return "system/vin/insertProduct";
    }

    @RequestMapping("queryByModelName")
    @ResponseBody
    public Product_model queryByModelName(Integer model_id){
        System.out.println(model_id);
        Product_model product = productServiceI.queryByModelName(model_id);
        if(product!=null){
            System.out.println(product);
        }
        return product;
    }

    /**
     * 新增产品信息
     * @param product
     * @return
     */
    @RequestMapping("insertProduct")
    public String insertProduct(Product product, Materials materials, DepotItem depotItem){
        System.out.println(product);
        boolean insertProduct = productServiceI.insertProduct(product);
        long product_type = product.getProduct_type();
        String mName = "";
        if (product_type==1){
            mName = "游戏本";
        }else if (product_type==2){
            mName = "商务本";
        }else if (product_type==3){
            mName = "台式机";
        }else {
            mName = "其他";
        }
        materials.setMName(mName);
        materials.setMType("成品");
        materials.setRemark("成品电脑");
        materials.setMUnit(product.getProduct_unit());
        materials.setPrice(product.getTrade_price());
        materials.setDesignation(product.getProduct_name());
        materialsServiceI.addMaterials(materials);
        depotItem.setBasicNumber(0);
        depotItem.setUnitPrice(product.getTrade_price());
        depotItem.setDepotId(1);
        depotItem.setAllPrice((double) 0);
        Materials materials1 = materialsServiceI.queryMaterialsByName(product.getProduct_name());
        depotItem.setMaterialId(materials1.getId());
         depotItemServiceI.addDepotItem(depotItem);
        if (insertProduct){
            return "system/vin/insertProduct";
        }else {
            return "";
        }
    }

    /**
     * 删除产品信息（修改删除标记）
     * @param id
     * @return
     */
    @RequestMapping("deleteProduct")
    @ResponseBody
    public String deleteProduct(Long id){
        System.out.println(id);
        boolean deleteProduct = productServiceI.deleteProduct(id);
        if(deleteProduct){
            return "true";
        }else {
            return "";
        }
    }

    /**
     * 修改产品信息
     * @param product
     * @return
     */
    @RequestMapping("updateProduct")
    @ResponseBody
    public String updateProduct(Product product){
        System.out.println(product);
        product.setCreatetime(new Date());
        System.out.println(product);
        boolean updateProduct = productServiceI.updateProduct(product);
        if (updateProduct){
            return "getAllProduct";
        }else {
            return "";
        }
    }

    /**
     * 根据产品id获取产品类型信息
     * @param product_type
     * @return
     */
    @RequestMapping("queryByProductType")
    @ResponseBody
    public Product_model queryByProductType(Long product_type){
        System.out.println(product_type);
        Product_model productType = productServiceI.queryByModelName(product_type);
        return productType;
    }

    @RequestMapping("deleteProducts")
    @ResponseBody
    public String deleteAll(Long[] proids){
        boolean bool = false;
        for (int i=0;i<proids.length;i++){
          bool = productServiceI.deleteProduct(proids[i]);
        }
        if (bool){
            return "true";
        }else {
            return "";
        }
    }

    @RequestMapping("updateProductStateT")
    @ResponseBody
    public String updateProductStateT(Long[] proidsT){
        boolean bool = false;
        boolean flag =false;
        for (int i=0;i<proidsT.length;i++){
            bool =   productServiceI.updateProductStateT(proidsT[i]);
        }
        if (bool){
            return "true";
        }else {
            return "";
        }
    }

    @RequestMapping("updateProductStateF")
    @ResponseBody
    public String updateProductStateF(Long[] proidsF){
        boolean bool = false;
        boolean flag = false;
        for (int i=0;i<proidsF.length;i++){
            bool = productServiceI.updateProductStateF(proidsF[i]);
        }
        if (bool){
            return "true";
        }else {
            return "";
        }
    }

    @RequestMapping("updateProducrtStatus")
    @ResponseBody
    public String updateProducrtStatus(long proStaid){
        boolean flag = productServiceI.updateProductStatus(proStaid);
        if (flag){
            return "system/vin/product";
        }else {
            return "";
        }
    }

    @RequestMapping("queryProductByProductName")
    @ResponseBody
    public String queryProductByProductName(String product_name){
        Product product = productServiceI.queryProductByProductName(product_name);
        if (product==null){
            return "true";
        }else {
            return "";
        }

    }
}
