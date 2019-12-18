package com.sxt.sys.mapper.qxs.finance;

import com.sxt.sys.domain.qxs.finance.FinancialSettlement;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 财务结算明细数据操作
 */
public interface FinancialSettlementMapper {

    /**
     * 查询所有未删除的记录
     * @return
     */
    @Select("select * from FinancialSettlement where deleteFlag !=1 order by balanceDate desc")
    List<FinancialSettlement> queryNotDeleteFs();

    /**
     * 查询所有已删除的数据
     * @return
     */
    @Select("select * from FinancialSettlement where deleteFlag =1 order by balanceDate desc")
    List<FinancialSettlement> queryDeleteFs();

    /**
     * 查询所有数据
     * @return
     */
    @Select("select * from FinancialSettlement order by balanceDate desc")
    List<FinancialSettlement> queryAllFinancialSettlement();

    /**
     * 根据id 获取当前数据
     * @param id
     * @return
     */
    @Select("select * FinancialSettlement where id=#{id} and deleteFlag != 1")
    FinancialSettlement getOneFs(FinancialSettlement id);

    /**
     * 修改描述
     * @param financialSettlement
     * @return
     */
    @Update("update FinancialSettlement set remark=#{remark} where id=#{id}")
    boolean updateRemark(FinancialSettlement financialSettlement);

    /**
     * 标记删除
     * @param deleteFlag 0 不删除; 1：删除
     * @param id
     * @return
     */
    @Update("update FinancialSettlement set deleteFlag =#{deleteFlag} where id=#{id}")
    boolean deleteFinancialSettlement(Integer deleteFlag, Integer id);

    /**
     * 新增财务结算记录
     * @param financialSettlement
     * @return
     */
    @Insert("insert into FinancialSettlement values(null,#{totalMoney},#{balanceDate}," +
            "#{userId},#{remark},0,#{type})")
    boolean addFinancialSettlement(FinancialSettlement financialSettlement);
}
