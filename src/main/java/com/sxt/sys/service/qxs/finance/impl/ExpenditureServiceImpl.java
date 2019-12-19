package com.sxt.sys.service.qxs.finance.impl;

import com.sxt.sys.domain.qxs.finance.Expenditure;
import com.sxt.sys.domain.qxs.finance.FinancialSettlement;
import com.sxt.sys.mapper.hjn.OrderMapper;
import com.sxt.sys.mapper.qxs.finance.ExpenditureMapper;
import com.sxt.sys.mapper.qxs.finance.FinancialSettlementMapper;
import com.sxt.sys.service.qxs.finance.ExpenditureServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExpenditureServiceImpl implements ExpenditureServiceI {

    @Autowired
    private ExpenditureMapper expenditureMapper;
    @Autowired
    private FinancialSettlementMapper financialSettlementMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 查询所有未删除的数据
     * @return
     */
    @Override
    public List<Expenditure> queryNotExpenditure() {
        return expenditureMapper.queryNotExpenditure();
    }

    /**
     * 查询所有已删除的数据
     * @return
     */
    @Override
    public List<Expenditure> queryDeleteExpenditure() {
        return expenditureMapper.queryDeleteExpenditure();
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Expenditure> queryAllExpenditure() {
        return expenditureMapper.queryAllExpenditure();
    }

    /**
     * 根据id进行查询
     * @param expenditureId
     * @return
     */
    @Override
    public Expenditure getOneExpenditure(Integer expenditureId) {
        return expenditureMapper.getOneExpenditure(expenditureId);
    }

    /**
     * 修改支出记录数据,修改收款账号，清算尾款
     * @param expenditure
     * @return
     */
    @Override
    public boolean updateExpenditure(Expenditure expenditure) {
        return expenditureMapper.updateExpenditure(expenditure);
    }

    /**
     * 修改付款状态
     * @param expenditure 状态；0：未付款；1：已付款；3：取消付款 默认0
     * @return
     */
    @Override
    public boolean confirmation(Expenditure expenditure) {
        return expenditureMapper.confirmation(expenditure);
    }

    /**
     * 标记删除
     * @param expenditure ；0：未删除；1：已删除
     * @return
     */
    @Override
    public boolean deleteExpenditure(Expenditure expenditure) {
        return expenditureMapper.deleteExpenditure(expenditure);
    }

    /**
     * 新增支出记录
     * @param expenditure
     * @return
     */
    @Override
    public boolean addExpenditure(Expenditure expenditure,Integer id) {
        boolean b = orderMapper.updateFinance(id);
        if(b){
            return expenditureMapper.addExpenditure(expenditure);
        }
        return false;
    }

    /**
     * 对支出进行结算 调用财务总结
     * @return
     */
    @Override
    public int sumExpenditure(FinancialSettlement fs) {
        //获取总金额
        Double sumExpenditure = expenditureMapper.sumExpenditure();
        if(sumExpenditure!=null && sumExpenditure>0){
            System.out.println("总金额："+sumExpenditure);
            // 修改结算状态
            boolean stateClose = expenditureMapper.updateStateClose(1);
            if(stateClose){
                fs.setBalanceDate(new Date());
                fs.setTotalMoney(sumExpenditure);
                fs.setType(1);
                System.out.println("fs对象："+fs);
                boolean b = financialSettlementMapper.addFinancialSettlement(fs);
                if(b){
                    return 0;
                }
            }
        }
        return 1;
    }

    /**
     * 清算尾款
     * @param expenditure
     * @return
     */
    @Override
    public boolean liquidationExpenditure(Expenditure expenditure) {
        return  expenditureMapper.liquidationExpenditure(expenditure);
    }
}
