package com.sxt.sys.service.winter;

import com.sxt.sys.domain.qxs.warehouse.DepotItem;
import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.winter.Sale;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/16 16:01
 * 销售业务处理接口
 */
public interface SaleServiceI {
    //通过财务审核修改状态
    /**
     * 未删除的销售数据
     * @return
     */
    List<HashMap> getAllNoDeleteSale();

    /**
     * 已删除的销售数据
     * @return
     */
    List<HashMap> getAllDeleteSale();

    /**
     * 修改销售删除标记
     * @param deleteFlag
     * @param id
     * @return
     */
    boolean deleteSale(int deleteFlag, int id);

    /**
     * 添加销售信息
     * 生成单据主表
     * @param sale
     * @return
     */
    boolean saveSaleAndDepotHead(Sale sale);

    /**
     * 修改销售状态
     * @param state
     * @param id
     * @return
     */
    boolean updateSaleState(int state, int id);

    /**
     * 修改所有销售信息
     * @param number
     * @param id
     * @return
     */
    boolean updateSaleNumber(Integer number,Integer id);

    /**
     * 查询单条销售数据
     * @param id
     * @return
     */
    HashMap findSaleOne(int id);
}
