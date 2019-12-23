package com.sxt.sys.mapper.hjn;

import com.sxt.sys.domain.hjn.Detailed;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: xterp
 * @description: DetailedMapper
 * @author: hjn
 * @create: 2019-12-16 09:50
 **/
@Repository
public interface DetailedMapper {
    /**
     /**
     * 新增采购明细
     *
     * @return
     */
    @Insert("insert into Detailed values(null,#{orderid},#{storehouseid},#{goodsid},#{number},#{tax},#{price},#{remarks})")
    int addDetailed(Detailed detailed);

    /**
     * 查询采购订单的明细
     * @return
     */
    //@Select("select * from Detailed  where orderid = #{orderid}")
    List<Detailed> queryDetailed(int orderid);

    /**
     * 修改采购订单的明细
     * @return
     */
    //@Select("update  Detailed set storehouseid=#{storehouseid},goodsid=#{goodsid},number=#{number},tax=#{tax},remarks=#{remarks}   where id = #{id}")
    int updateDetailed(Detailed detailed);

    /**
     * 删除采购订单的明细
     * @return
     */
    //Select("delect from Detailed  where id = #{id}")
    int updateDetailed(int id);
}
