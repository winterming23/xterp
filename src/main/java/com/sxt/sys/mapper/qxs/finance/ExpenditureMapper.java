package com.sxt.sys.mapper.qxs.finance;

import com.sxt.sys.domain.qxs.finance.Expenditure;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 财政支出记录数据操作
 */
public interface ExpenditureMapper {

    /**
     * 查询所有未删除的支出信息
     * @return
     */
    @Select("select * from expenditure where deleteFlag !=1 and confirmPayment=1 order by dateReceopt desc")
    List<Expenditure> queryNotExpenditure();

    /**
     * 查询所有已删除的支出信息
     * @return
     */
    @Select("select * from expenditure where deleteFlag =1 order by dateReceopt desc")
    List<Expenditure> queryDeleteExpenditure();

    /**
     * 查询所有数据
     * @return
     */
    @Select("select * from expenditure order by dateReceopt desc")
    List<Expenditure> queryAllExpenditure();

    /**
     * 根据id获取当前数据
     * @param expenditureId
     * @return
     */
    @Select("select * from expenditure where expenditureId=#{expenditureId} and deleteFlag !=1 ")
    Expenditure getOneExpenditure(Integer expenditureId);

    /**
     * 修改支出记录数据,修改收款账号，清算尾款
     * @param expenditure
     * @return
     */
    @Update("update expenditure set account=#{account}, remark=#{remark},principal=#{principal} where expenditureId=#{expenditureId}")
    boolean updateExpenditure(Expenditure expenditure);

    /**
     * 是否确认付款
     * @param expenditure
     * @return
     */
    @Update("update expenditure set confirmPayment=#{confirmPayment} where expenditureId=#{expenditureId}")
    boolean confirmation(Expenditure expenditure);

    /**
     * 标记删除
     * @param expenditure
     * @return
     */
    @Update("update expenditure set deleteFlag=#{deleteFlag} where expenditureId=#{expenditureId}")
    boolean deleteExpenditure(Expenditure expenditure);

    /**
     * 结算总支出
     * @return
     */
    @Select("select sum(actualPayment) from expenditure where deleteFlag !=1 " +
            "and confirmPayment =1 and stateClose=0 ")
    Double sumExpenditure();

    /**
     * 新增支出记录
     * @param expenditure
     * @return
     */
    @Insert("insert into expenditure values(null,#{type},#{paymentAmount},#{actualPayment}," +
            "#{balancePayment},#{dateReceopt},#{account},0,#{remark},#{principal},0,0)")
    boolean addExpenditure(Expenditure expenditure);

    /**
     * 修改结算状态
     * @param stateClose 结算状态 0 未结算 1 已结算
     * @param
     * @return
     */
    @Update("update expenditure set stateClose=#{stateClose} ")
    boolean updateStateClose(Integer stateClose);

    /**
     * 清算尾款
     * @param expenditure
     * @return
     */
    @Update("update expenditure actualPayment=#{actualPayment},balancePayment=#{balancePayment} where expenditureId=#{expenditureId}")
    boolean liquidationExpenditure(Expenditure expenditure);
}
