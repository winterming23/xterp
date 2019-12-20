package com.sxt.sys.service.vin;

import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.vin.Product_type;

import java.util.HashMap;
import java.util.List;

public interface ProductModelsServiceI {
    /**
     * 查询所有产品型号
     * @return
     */
    public List<HashMap> getAllProductModels();

    /**
     * 新增产品型号
     * @param product_model
     * @return
     */
    public boolean insertProductModels(Product_model product_model);

    /**
     * 修改产品型号信息
     * @param product_model
     * @return
     */
    public boolean updateProductModels(Product_model product_model);

    /**
     * 修改产品型号删除标记
     * @param id
     * @return
     */
    public boolean deleteProductModels(long id);

    /**
     * 获取产品类别
     * @return
     */
    public List<Product_type> queryType();

    /**
     * 修改审核状态
     * @param id
     * @return
     */
    public boolean updateProductModelsStatus(long id);

    /**
     * 根据id 查询产品型号信息
     * @param id
     * @return
     */
    public Product_model queryByModelId(long id);
}
