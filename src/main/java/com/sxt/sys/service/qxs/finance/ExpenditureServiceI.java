package com.sxt.sys.service.qxs.finance;

import com.sxt.sys.domain.qxs.finance.Expenditure;
import com.sxt.sys.domain.qxs.finance.FinancialSettlement;

import java.util.List;

/**
 * 财政支出记录业务操作
 */
public interface ExpenditureServiceI {

    /**
     * 查询所有未删除的支出信息
     * @return
     */
    List<Expenditure> queryNotExpenditure();

    /**
     * 查询所有已删除的支出信息
     * @return
     */
    List<Expenditure> queryDeleteExpenditure();

    /**
     * 查询所有数据
     * @return
     */
    List<Expenditure> queryAllExpenditure();

    /**
     * 根据id获取当前数据
     * @param expenditureId
     * @return
     */
    Expenditure getOneExpenditure(Integer expenditureId);

    /**
     * 修改支出记录数据,修改收款账号，清算尾款
     * @param expenditure
     * @return
     */
    boolean updateExpenditure(Expenditure expenditure);

    /**
     * 是否确认付款
     * @param state 状态；0：未付款；1：已付款；3：取消付款 默认0
     * @return
     */
    boolean confirmation(Integer state, Integer id);

    /**
     * 标记删除
     * @param deleteFlag ；0：未删除；1：已删除
     * @param id
     * @return
     */
    boolean deleteExpenditure(Integer deleteFlag, Integer id);

    /**
     * 新增支出记录
     * @param expenditure
     * @return
     */
    boolean addExpenditure(Expenditure expenditure, Integer id);

    /**
     * 结算总支出
     * @return
     */
    int sumExpenditure(FinancialSettlement fs);

    /**
     * 清算尾款
     * @param expenditure
     * @return
     */
    boolean liquidationExpenditure(Expenditure expenditure);
}
