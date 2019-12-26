package com.sxt.sys.service.zqw;


import com.sxt.sys.domain.zqw.Picking;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 领料业务操作接口
 */
public interface PickingServiceI {
    /**
     * 查询领料信息
     * @return
     */
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
     * 修改审核状态
     * @param id
     * @param productionAudit
     * @return
     */
    boolean pickingSh(@Param("id") int id, @Param("productionAudit") int productionAudit);
    /**
     * 根据生产id查询领料数据
     * @param id
     * @return
     */
    List<HashMap> seleDgpick(int id);
    /**
     * 根据生产id查询名字
     * @param id
     * @return
     */
    List<HashMap> seleDguser(int id);
    /**
     * 根据生产id查询所需生产的名称
     * @param id
     * @return
     */
    List<HashMap> seleDgProduct(int id);
}
