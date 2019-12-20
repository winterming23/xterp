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
    public boolean saveSaleAndDepotHead(Sale sale, Depothead depothead, DepotItem depotItem) throws ParseException {
        //添加单据主表
        depotHeadMappers.addDepotHead(depothead);
        //查询是否存在该单据子表
        DepotItem depotItems = depotItemMappers.queryDepotItemRecord(depotItem.getId());
        //修改单据子表中的数量 数量=单据数量-销售数量 编号为查询编号
        int amount = depotItems.getBasicNumber() - sale.getNumber();
        //单据子表存在时
        if (depotItems != null) {
            //判断单据中的数量小于5时生成 生产申请表
            if (depotItems.getBasicNumber() < 5){
                //生产数量 在需要销售的基础上 +100
                int number = sale.getNumber() + 100;
                ApplyFor applyFor = new ApplyFor(null,"生产计划申请",sale.getProductId(),number,"生产申请：缺少成品",0,sale.getUserId(),null,0);
                applyForMappers.saveApplyFor(applyFor);
            }
            DepotItem item = new DepotItem();
            item.setBasicNumber(amount);
            item.setId(depotItems.getId());
            depotItemMappers.updateAmount(item);

        }
        return saleMapper.saveSale(sale);
    }
}
