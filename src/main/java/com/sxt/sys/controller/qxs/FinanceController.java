package com.sxt.sys.controller.qxs;

import com.sxt.sys.domain.qxs.finance.Expenditure;
import com.sxt.sys.domain.qxs.finance.FinancialSettlement;
import com.sxt.sys.domain.qxs.finance.Income;
import com.sxt.sys.service.qxs.finance.ExpenditureServiceI;
import com.sxt.sys.service.qxs.finance.FinancialSettlementServiceI;
import com.sxt.sys.service.qxs.finance.IncomeServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 财政试图层
 */
@Controller
@RequestMapping("finance/")
public class FinanceController {

    @Autowired
    private FinancialSettlementServiceI financialSettlementServiceI;
    @Autowired
    private ExpenditureServiceI expenditureServiceI;
    @Autowired
    private IncomeServiceI incomeServiceI;

    @RequestMapping("pageFinance")
    public String pageFinance(){
        return "system/qxs/finance/financeMain";
    }

    /**
     * 查询所有汇总记录,图形报表数据绑定
     * @return
     */
    @RequestMapping("queryNotDeleteFs")
    @ResponseBody
    public List queryNotDeleteFs(){
        List<FinancialSettlement> financialSettlements = financialSettlementServiceI.queryNotDeleteFs();
        if(financialSettlements.size()>0){
            System.out.println(financialSettlements.size()>0);
            for (FinancialSettlement financialSettlement : financialSettlements) {
                System.out.println(financialSettlement);
            }
        }
        return financialSettlements;
    }

    /**
     * 查询所有未删除的支出与收入记录
     * @return
     */
    @ResponseBody
    @RequestMapping("queryNotDeleteFinance")
    public Map queryNotDeleteFinance(){
        //查询支出
        List<Expenditure> expenditures = expenditureServiceI.queryNotExpenditure();
        //查询收入
        List<Income> incomes = incomeServiceI.queryNotDeleteIncome();
        HashMap<Integer, List> map = new HashMap<Integer, List>();
        map.put(1,expenditures);
        map.put(2,incomes);
        return map;
    }

    /**
     * 查询汇总详情，进行页面跳转
     * @return
     */
    @RequestMapping("pageDetailsFs")
    public String pageDetailsFs(Model model){
        System.out.println("url:pageDetailsFs");
        List<FinancialSettlement> fs = financialSettlementServiceI.queryAllFinancialSettlement();
        model.addAttribute("fsList",fs);
        return "system/qxs/finance/FinancialSettlement";
    }

    /**
     * 标记删除 汇总数据
     * @return
     */
    @RequestMapping("deleteFlagFs")
    @ResponseBody
    public boolean deleteFlagFs(Integer flag,Integer id){
        System.err.println(id);
        FinancialSettlement financialSettlement = new FinancialSettlement();
        financialSettlement.setDeleteFlag(flag);
        financialSettlement.setId(id);
        System.err.println(financialSettlement);
        return financialSettlementServiceI.deleteFinancialSettlement(financialSettlement);
    }

    /**
     *  跳转值财务详情页面
     * @return
     */
    @RequestMapping("pageFinanceDetails")
    public String pageFinanceDetails(Model model){
        //查询所有支出记录
        List<Expenditure> expenditures = expenditureServiceI.queryAllExpenditure();
        //查询所有收入记录
        List<Income> incomes = incomeServiceI.queryAllIncome();
        model.addAttribute("expendList",expenditures);
        model.addAttribute("incomeList",incomes);
        return "system/qxs/finance/financeDetails";
    }

    /**
     * 是否确定收款
     * @param confirm_receipt 审批字段
     * @param id
     * @param type 判断是收款或付款
     * @return
     */
    @RequestMapping("affirm")
    @ResponseBody
    public boolean affirm(Integer confirm_receipt,Integer id,String type,Integer clientId){
        System.err.println(confirm_receipt+"=="+id+"=="+type);
        if("支出".equals(type)){
            Expenditure expenditure = new Expenditure();
            expenditure.setConfirmPayment(confirm_receipt);
            expenditure.setExpenditureId(id);
            System.err.println(expenditure);
            return expenditureServiceI.confirmation(expenditure);
        }else if("收入".equals(type)){
            Income income = new Income();
            income.setConfirmReceipt(confirm_receipt);
            income.setIncomeId(id);
            System.err.println(income);
            return incomeServiceI.confirmation(income,clientId);
        }
        return false;
    }

    /**
     * 收支记录删除
     * @param flag 标记删除字段
     * @param id 条件
     * @param type 判断 收入/支出
     * @return
     */
    @RequestMapping("expurgate")
    @ResponseBody
    public boolean expurgate(Integer flag,Integer id,String type){
        System.err.println(flag+"=="+id+"=="+type);
        if("支出".equals(type)){
            Expenditure expenditure = new Expenditure();
            expenditure.setExpenditureId(id);
            expenditure.setDeleteFlag(flag);
            System.err.println(expenditure);
            return expenditureServiceI.deleteExpenditure(expenditure);
        }else if("收入".equals(type)){
            Income income = new Income();
            income.setIncomeId(id);
            income.setDeleteFlag(flag);
            System.err.println(income);
            return incomeServiceI.deleteIncome(income);
        }
        return false;
    }

    /**
     * 查询整个项目的支出/收入
     * @return
     */
    @RequestMapping("addReady")
    public String addReady(Model model){
        Map map = incomeServiceI.addReady();
        model.addAttribute("readys",map);
        return "system/qxs/finance/appendRecord";
    }

    /**
     * 新增支出记录
     * @param expenditure
     * @return
     */
    @RequestMapping("addExpenditure")
    @ResponseBody
    public boolean addExpenditure(Expenditure expenditure,Integer orderId){
        System.err.println(orderId+"支出对象:"+expenditure);
        //设置创建时间
        expenditure.setDateReceopt(new Date());
        //计算尾款
        expenditure.setBalancePayment(expenditure.getPaymentAmount()-expenditure.getActualPayment());
        return expenditureServiceI.addExpenditure(expenditure,orderId);
    }

    /**
     * 新增收入记录
     * @param income
     * @param id
     * @return
     */
    @RequestMapping("addIncome")
    @ResponseBody
    public boolean addIncome(Income income,Integer id){
        //计算尾款
        income.setBalancePayment(income.getPayable()-income.getActualPayment());
        //获取当前时间
        income.setDateRecorded(new Date());
        return incomeServiceI.addIncome(income,id);
    }

    /**
     * 跳转至修改页面
     * @param id
     * @param type
     * @return
     */
    @RequestMapping("toChange")
    public String toChange(Integer id, Integer type, Model model){
        if(type==0){
            //支出
            Expenditure expenditure = expenditureServiceI.getOneExpenditure(id);
            model.addAttribute("ex",expenditure);
        }else{
            //收入
            Income income = incomeServiceI.getOneIncome(id);
            model.addAttribute("in",income);
        }
        return "system/qxs/finance/updateFinance";
    }

    /**
     *  修改支出记录
     * @param expenditure
     * @return
     */
    @ResponseBody
    @RequestMapping("updateExpenditure")
    public boolean updateExpenditure(Expenditure expenditure,Double number){
        //boolean b = false;
        System.err.println(expenditure);
        if(number>0){
            expenditure.setActualPayment(expenditure.getActualPayment()+number);
            expenditure.setBalancePayment(expenditure.getBalancePayment()-number);
            expenditureServiceI.liquidationExpenditure(expenditure);
        }
        return expenditureServiceI.updateExpenditure(expenditure);
    }

    /**
     * 修改收入记录
     * @param income
     * @param number 本次结算金额
     * @return
     */
    @ResponseBody
    @RequestMapping("updateIncome")
    public boolean updateIncome(Income income,Double number){
        System.err.println(income);
        System.out.println(number);
        boolean b =false;
        if(number>0){
            //设置清算后的已付金额
            income.setActualPayment(income.getActualPayment()+number);
            //设置清算后的尾款
            income.setBalancePayment(income.getBalancePayment()-number);
            incomeServiceI.liquidationIncome(income);
        }
        return incomeServiceI.updateIncome(income);
    }

    /**
     * 收支 记录 结算
     * @param fs
     * @return
     */
    @ResponseBody
    @RequestMapping("sum")
    public int sum(FinancialSettlement fs){
        System.err.println(fs);
        //支出结算
        int expenditure = expenditureServiceI.sumExpenditure(fs);
        //收入结算
        int income = incomeServiceI.sumIncome(fs);
        if(expenditure==0 && income==0){
            return 0;
        }else if(expenditure==1){
            return 1;
        }else if(income==1){
            return 2;
        }
        return 3;
    }
}
