package com.sxt.sys.service.qxs.finance;

import com.sxt.sys.domain.qxs.finance.FinancialSettlement;
import com.sxt.sys.domain.qxs.finance.Income;

import java.util.List;
import java.util.Map;

/**
 * 财务收入业务实现
 */
public interface IncomeServiceI {

    /**
     * 查询所有未删除的财政收入记录
     * @return
     */
    List<Income> queryNotDeleteIncome();

    /**
     * 查询所有删除的财政收入记录
     * @return
     */
    List<Income> queryDeleteIncome();

    /**
     * 查询所有记录 包括删除的数据
     * @return
     */
    List<Income> queryAllIncome();

    /**
     * 根据id获取当前记录
     * @param incomeId
     * @return
     */
    Income  getOneIncome(Integer incomeId);

    /**
     * 是否确认收款
     * @param state 0：未收款；1：已收款 审批字段
     * @param id
     * @return
     */
    boolean confirmation(Integer state, Integer id);

    /**
     * 修改收入记录
     * @param income
     * @return
     */
    boolean updateIncome(Income income);

    /**
     * 清算尾款
     * @param balance_payment
     * @param id
     * @return
     */
    boolean liquidationIncome(Double actualPayment, Double balance_payment, Integer id);

    /**
     * 标记删除
     * @param deleteFlag 1：删除; 0：不删除
     * @param id 条件
     * @return
     */
    boolean deleteIncome(Integer deleteFlag, Integer id);

    /**
     * 结算总收入
     * @return
     */
    int sumIncome(FinancialSettlement fs);

    /**
     * 新增收入记录
     * @param income
     * @return
     */
    boolean addIncome(Income income, Integer id);

    /**
     * 查询整个项目的支出/收入
     * @return
     */
    Map addReady();
}
