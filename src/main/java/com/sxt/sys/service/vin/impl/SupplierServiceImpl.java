package com.sxt.sys.service.vin.impl;

import com.sxt.sys.domain.vin.Supplier;
import com.sxt.sys.mapper.vin.SupplierMapper;
import com.sxt.sys.service.vin.SupplierServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("all")
public class SupplierServiceImpl implements SupplierServiceI {
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> getAllSupplierG() {
        return supplierMapper.getAllSupplierG();
    }

    @Override
    public List<Supplier> getAllSupplierK() {
        return supplierMapper.getAllSupplierK();
    }

    @Override
    public boolean deleteSupplier(long id) {
        return supplierMapper.deleteSupplier(id);
    }

    @Override
    public boolean insertSupplierG(Supplier supplier) {
        return supplierMapper.insertSupplierG(supplier);
    }

    @Override
    public boolean insertSupplierK(Supplier supplier) {
        return supplierMapper.insertSupplierK(supplier);
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return supplierMapper.updateSupplier(supplier);
    }

    @Override
    public boolean updateSupplierStatus(long id) {
        return supplierMapper.updateSupplierStatus(id);
    }

    @Override
    public List<Supplier> queryBySupplierId(long id) {
        return supplierMapper.queryBySupplierId(id);
    }

    @Override
    public boolean updateSupplierEnabledT(long id) {
        return supplierMapper.updateSupplierEnabledT(id);
    }

    @Override
    public boolean updateSupplierEnabledF(long id) {
        return supplierMapper.updateSupplierEnabledF(id);
    }
}
