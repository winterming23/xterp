package com.sxt.sys.mapper.qxs.finance;

import com.sxt.sys.domain.qxs.finance.Income;
import com.sxt.sys.domain.vin.Supplier;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 财政收入明细数据操作
 */
public interface IncomeMapper {

    /**
     * 查询所有未删除的财政收入记录
     * @return
     */
    @Select("select * from income where deleteFlag!=1 and confirmReceipt=1 order by dateRecorded desc")
    List<Income> queryNotDeleteIncome();

    /**
     * 查询所有删除的财政收入记录
     * @return
     */
    @Select("select * from income where deleteFlag=1 order by dateRecorded desc")
    List<Income> queryDeleteIncome();

    /**
     * 查询所有记录 包括删除的数据
     * @return
     */
    @Select("select * from income order by dateRecorded desc")
    List<Income> queryAllIncome();

    /**
     * 根据id获取当前记录
     * @param incomeId
     * @return
     */
    @Select("select * from income where incomeId=#{incomeId} and deleteFlag!=1")
    Income  getOneIncome(Integer incomeId);

    /**
     * 是否确认收款
     * @param income
     * @return
     */
    @Update("update income set confirmReceipt=#{confirmReceipt} where incomeId=#{incomeId}")
    boolean confirmation(Income income);

    /**
     * 修改收入记录
     * @param income
     * @return
     */
    @Update("update income set remark=#{remark} where incomeId=#{incomeId}")
    boolean updateIncome(Income income);

    /**
     * 清算尾款
     * @param income
     * @return
     */
    @Update("update income set actualPayment=#{actualPayment},balancePayment=#{balancePayment} where incomeId=#{incomeId}")
    boolean liquidationIncome(Income income);

    /**
     * 标记删除
     * @param income
     * @return
     */
    @Update("update income set deleteFlag=#{deleteFlag} where incomeId=#{incomeId}")
    boolean deleteIncome(Income income);

    /**
     * 结算总收入
     * @return
     */
    @Select("select sum(actualPayment) from income where deleteFlag !=1 and confirmReceipt =1 and stateClose=0")
    Double sumIncome();

    /**
     * 新增收入记录
     * @param income
     * @return
     */
    @Insert("insert into income values(null,#{clientId},#{payable}," +
            "#{actualPayment},#{balancePayment},#{dateRecorded},0,#{remark},#{principal},0,#{paymentMethod},0)")
    boolean addIncome(Income income);

    /**
     * 修改结算状态
     * @param stateClose 结算状态 0 未结算 1 已结算
     * @param
     * @return
     */
    @Update("update income set stateClose=#{stateClose} ")
    boolean updateStateClose(Integer stateClose);

    /**
     * 根据id查询供应商
     * @param clientId
     * @return
     */
    @Select("select * from supplier where id=#{clientId}")
    Supplier querySupplier(Integer clientId);

    /**
     * 根据供应商编号修改订单表状态
     * @param clientId
     * @return
     */
    @Update("update orders set orderstate=9 where supplierid=#{clientId}")
    boolean upOrders(Integer clientId);
}
