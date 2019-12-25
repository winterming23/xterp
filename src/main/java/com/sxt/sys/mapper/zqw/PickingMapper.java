package com.sxt.sys.mapper.zqw;

import com.sxt.sys.domain.zqw.Picking;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * 领料持久层操作接口
 */
public interface PickingMapper {
    /**
     * 查询领料信息
     * @return
     */
    @Select("select picking.*,userInfo.userName,productionplan.*,product.product_name FROM picking,productionplan,product,userInfo where productionplan.pickingid=picking.id and productionplan.personCharge=userinfo.id")
    List<HashMap> selePicking();

    /**
     * 添加领料信息
     * @param picking
     * @return
     */
    boolean inserPicking(Picking picking);

    /**
     * 修改领料信息
     * @param picking
     * @return
     */
    boolean updatePicking(Picking picking);

    /**
     * 根据id删除领料信息
     * @param id
     * @return
     */
    boolean deletePicking(int id, int deletePick);

    /**
     * 根据编号查询
     * @param id
     * @return
     */
    @Select("select * from picking where id=#{id}")
    Picking getOnePicking(Integer id);
}
