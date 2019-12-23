package com.sxt.sys.service.vin.impl;

import com.sxt.sys.domain.vin.Product;
import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.vin.Product_type;
import com.sxt.sys.mapper.vin.ProductMapper;
import com.sxt.sys.service.vin.ProductServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@SuppressWarnings("all")
public class ProductServiceImpl implements ProductServiceI {
    @Autowired
    private ProductMapper productMapper;


    @Override
    public List<HashMap> getAllProduct() {
        return productMapper.getAllProduct();
    }

    @Override
    public List<Product_type> getProductType() {
        return productMapper.getProductType();
    }

    @Override
    public List<Product_model> getProductModel() {
        return productMapper.getProductModel();
    }

    @Override
    public Product getProductById(long id) {
        return productMapper.getProductById(id);
    }

    @Override
    public boolean insertProduct(Product product) {
        return productMapper.insertProduct(product);
    }

    @Override
    public boolean updateProduct(Product product) {
        return productMapper.updateProduct(product);
    }

    @Override
    public boolean deleteProduct(long id) {
        return productMapper.deleteProduct(id);
    }

    @Override
    public Product_model getProductModelById(long id) {
        return productMapper.getProductModelById(id);
    }

    @Override
    public Product_type queryByModelName(long id) {
        return productMapper.queryByModelName(id);
    }

    @Override
    public Product queryByProductType(long product_type) {
        return productMapper.queryByProductType(product_type);
    }

    @Override
    public boolean updateProductStateF(long id) {
        return productMapper.updateProductStateF(id);
    }

    @Override
    public boolean updateProductStateT(long id) {
        return productMapper.updateProductStateT(id);
    }

    @Override
    public boolean updateProductStatus(long id) {
        return productMapper.updateProductStatus(id);
    }
}
