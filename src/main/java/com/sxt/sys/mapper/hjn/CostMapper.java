package com.sxt.sys.mapper.hjn;

import com.sxt.sys.domain.hjn.Cost;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: xterp
 * @description: CostMapper
 * @author: hjn
 * @create: 2019-12-16 09:49
 **/
@Repository
public interface CostMapper {
    /**
     * 添加采购支出
     *
     * @return
     */
    //@Insert("(insert into cost values(null,#{orderid},#{costtype},#{costprice}))")
    int addCost(Cost cost);


    /**
     * 删除采购支出
     *
     * @return
     */
    //@Delete("(delect from cost where id=#{id} )")
    int delectCost(int id);

    /**
     * 查采购支出
     *
     * @return
     */
    // @Select("(delect from cost where orderid=#{orderid} )")
    List<Cost> findCost(int orderid);

    /**
     * 修改采购支出
     *
     * @return
     */
    //@Update("(update  cost set costprice=#{costprice},costtype=#{costtype}  where id=#{id} )")
    int delectCost(int id, String costtype, int costprice);
}
