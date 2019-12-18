package com.sxt.sys.service.qxs.finance;

import com.sxt.sys.domain.qxs.finance.FinancialSettlement;

import java.util.List;

/**
 * 财政结算业务操作
 */
public interface FinancialSettlementServiceI {

    /**
     * 查询所有未删除的记录
     * @return
     */
    List<FinancialSettlement> queryNotDeleteFs();

    /**
     * 查询所有已删除的数据
     * @return
     */
    List<FinancialSettlement> queryDeleteFs();

    /**
     * 查询所有数据
     * @return
     */
    List<FinancialSettlement> queryAllFinancialSettlement();

    /**
     * 根据id 获取当前数据
     * @param id
     * @return
     */
    FinancialSettlement getOneFs(FinancialSettlement id);

    /**
     * 修改描述
     * @param financialSettlement
     * @return
     */
    boolean updateRemark(FinancialSettlement financialSettlement);

    /**
     * 标记删除
     * @param deleteFlag 0 不删除; 1：删除
     * @param id
     * @return
     */
    boolean deleteFinancialSettlement(Integer deleteFlag, Integer id);

    /**
     * 新增财务结算记录
     * @param financialSettlement
     * @return
     */
    boolean addFinancialSettlement(FinancialSettlement financialSettlement);
}
