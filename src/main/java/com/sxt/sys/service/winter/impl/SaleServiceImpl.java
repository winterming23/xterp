package com.sxt.sys.service.winter.impl;

import com.sxt.sys.domain.qxs.warehouse.DepotItem;
import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.winter.ApplyFor;
import com.sxt.sys.domain.winter.Sale;
import com.sxt.sys.mapper.qxs.warehouse.DepotHeadMapper;
import com.sxt.sys.mapper.qxs.warehouse.DepotItemMapper;
import com.sxt.sys.mapper.winter.ApplyForMapper;
import com.sxt.sys.mapper.winter.SaleMapper;
import com.sxt.sys.service.winter.SaleServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/16 16:30
 */
@SuppressWarnings("all")
@Service
public class SaleServiceImpl implements SaleServiceI {
    @Autowired
    private SaleMapper saleMapper;//销售
    @Autowired
    private DepotHeadMapper depotHeadMappers;//单据主表
    @Autowired
    private DepotItemMapper depotItemMappers;//单据子表
    @Autowired
    private ApplyForMapper applyForMappers;//生产申请
    /**
     * 未删除的销售数据
     * @return
     */
    @Override
    public List<HashMap> getAllNoDeleteSale() {
        return saleMapper.getAllNoDeleteSale();
    }

    /**
     * 已删除的销售数据
     * @return
     */
    @Override
    public List<HashMap> getAllDeleteSale() {
        return saleMapper.getAllDeleteSale();
    }

    /**
     * 修改销售删除标记
     * @param deleteFlag
     * @param id
     * @return
     */
    @Override
    public boolean deleteSale(int deleteFlag, int id) {
        return saleMapper.deleteSale(deleteFlag,id);
    }

    /**
     * 添加销售信息
     * 生成单据主表
     * @param sale
     * @param depothead
     * @return
     */
    @Override
    public boolean saveSaleAndDepotHead(Sale sale) throws ParseException {
        return saleMapper.saveSale(sale);
    }

    /**
     * 修改销售状态
     *
     * @param state
     * @param id
     * @return
     */
    @Override
    public boolean updateSaleState(int state, int id) {
        return saleMapper.updateSaleState(state,id);
    }

    /**
     * 修改所有销售信息
     *
     * @param number
     * @param id
     * @return
     */
    @Override
    public boolean updateSaleNumber(Integer number,Integer id) {
        return saleMapper.updateSaleNumber(number,id);
    }

    /**
     * 查询单条销售数据
     *
     * @param id
     * @return
     */
    @Override
    public HashMap findSaleOne(int id) {
        return saleMapper.findSaleOne(id);
    }
}
