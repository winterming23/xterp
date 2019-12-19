package com.sxt.sys.service.vin;

import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.vin.Product;
import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.vin.Product_type;

import java.util.HashMap;
import java.util.List;

/**
 * 产品业务接口
 */
public interface ProductServiceI {
    /**
     * 查询所有产品
     * @return List<Product>
     */
    public List<HashMap> getAllProduct();

    /**
     * 查询所有的产品类别
     * @return List<Product_type>
     */
    public List<Product_type> getProductType();

    /**
     * 查询所有产品型号
     * @return List<Product_model>
     */
    public List<Product_model> getProductModel();

    /**
     * 根据产品id查询
     * @return Product
     */
    public Product getProductById(long id);

    /**
     * 添加新的产品信息
     * @param product
     * @return boolean
     */
    public boolean insertProduct(Product product);

    /**
     * 修改产品信息
     * @param product
     * @return boolean
     */
    public boolean updateProduct(Product product);

    /**
     * 删除产品信息（删除标记）
     * @param id
     * @return boolean
     */
    public boolean deleteProduct(long id);

    /**
     * 查询产品型号
     * @param id
     * @return Product_model
     */
    public Product_model getProductModelById(long id);

    /**
     * 查询产品库存信息
     * @return List<Depothead>
     */
    public List<Depothead> getProductStock();

    /**
     * 根据产品型号获取产品类型
     * @return Product_type
     */
    public Product_type queryByModelName(long id);

    /**
     * 根据产品类型获取类型名称
     * @param product_type
     * @return
     */
    public Product queryByProductType(long product_type);

    /**
     * 修改产品状态为禁用
     * @param id
     * @return boolean
     */
    public boolean updateProductStateF(long id);

    /**
     * 修改产品状态为启用
     * @param id
     * @return boolean
     */
    public boolean updateProductStateT(long id);

    /**
     * 修改产品审核状态
     * @param id
     * @return
     */
    public boolean updateProductStatus(long id);
}
