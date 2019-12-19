package com.sxt.sys.service.zqw;

import com.sxt.sys.domain.zqw.Picking;

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
}
