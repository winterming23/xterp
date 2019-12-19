package com.sxt.sys.service.qxs.finance.impl;

import com.sxt.sys.domain.qxs.finance.FinancialSettlement;
import com.sxt.sys.mapper.qxs.finance.FinancialSettlementMapper;
import com.sxt.sys.service.qxs.finance.FinancialSettlementServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialSettlementServiceImpl implements FinancialSettlementServiceI {

    @Autowired
    private FinancialSettlementMapper financialSettlementMapper;

    /**
     * 查询所有未删除的记录
     * @return
     */
    @Override
    public List<FinancialSettlement> queryNotDeleteFs() {
        return financialSettlementMapper.queryNotDeleteFs();
    }

    /**
     * 查询所有已删除的数据
     * @return
     */
    @Override
    public List<FinancialSettlement> queryDeleteFs() {
        return financialSettlementMapper.queryDeleteFs();
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<FinancialSettlement> queryAllFinancialSettlement() {
        return financialSettlementMapper.queryAllFinancialSettlement();
    }

    /**
     * 根据id获取记录
     * @param id
     * @return
     */
    @Override
    public FinancialSettlement getOneFs(Integer id) {
        return financialSettlementMapper.getOneFs(id);
    }

    /**
     * 修改记录数据 描述
     * @param financialSettlement
     * @return
     */
    @Override
    public boolean updateRemark(FinancialSettlement financialSettlement) {
        return financialSettlementMapper.updateRemark(financialSettlement);
    }

    /**
     * 标记删除
     * @param financialSettlement
     * @return
     */
    @Override
    public boolean deleteFinancialSettlement(FinancialSettlement financialSettlement) {
        return financialSettlementMapper.deleteFinancialSettlement(financialSettlement);
    }

    /**
     * 新增 结算记录
     * @param financialSettlement
     * @return
     */
    @Override
    public boolean addFinancialSettlement(FinancialSettlement financialSettlement) {
        return financialSettlementMapper.addFinancialSettlement(financialSettlement);
    }
}
