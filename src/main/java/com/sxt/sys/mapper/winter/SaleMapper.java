package com.sxt.sys.mapper.winter;

import com.sxt.sys.domain.winter.Sale;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/10 11:13
 * 销售数据持久操作
 */
public interface SaleMapper {
    /**
     * 添加销售信息
     * @param sale
     * @return
     */
    boolean saveSale(Sale sale);

    /**
     * 修改销售状态
     * @param state
     * @param id
     * @return
     */
    boolean updateSaleState(@Param("state") int state,
                            @Param("id") int id);

    /**
     * 修改所有销售信息
     * @param sale
     * @return
     */
    boolean updateSale(Sale sale);

    /**
     * 修改销售删除标记
     * @param deleteFlag
     * @param id
     * @return
     */
    boolean deleteSale(@Param("deleteFlag") int deleteFlag,
                       @Param("id") int id);

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
     * 查询所有销售数据
     * @return
     */
    List<HashMap> getAllSale();

    /**
     * 查询单条销售数据
     * @param id
     * @return
     */
    Sale findSaleOne(int id);

    /**
     * 查询所有销售收款
     * @return
     */
    @Select(" select s.id saleId,s.userid userId,su.id suId,su.supplier suName," +
            " su.accountNumber account,s.money money,s.reality reality,s.commission commission from sale s " +
            " LEFT JOIN supplier su on s.clientId=su.id" +
            " where s.state=2 and su.type='客户' and su.delete_flag=0  and s.deleteFlag=0 and s.finance=0")
    List<HashMap> salesProceeds();

    /**
     * 修改财务添加状态
     * @return
     */
    @Update("update sale set finance=1 where id=#{id}")
    boolean updateFinance(Integer id);
}
