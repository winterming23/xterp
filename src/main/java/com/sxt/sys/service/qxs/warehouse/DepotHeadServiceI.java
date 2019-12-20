package com.sxt.sys.service.qxs.warehouse;

import com.sxt.sys.domain.qxs.warehouse.Depothead;

import java.util.HashMap;
import java.util.List;

/**
 * 单据主表业务操作
 */
public interface DepotHeadServiceI {
    /**
     * 查询所有未删除的单据主表
     * @return
     */
    List<Depothead> queryNotDeleteDepotHead();

    /**
     * 查询所有数据,包括删除的数据
     * @return
     */
    List<Depothead> queryAllDepotHead();

    /**
     * 根据票据号查询当前记录
     * @param number
     * @return
     */
    List<Depothead> getOneDepotHead(String number);

    /**
     * 查询被删除的数据
     * @return
     */
    List<Depothead> queryDelete();

    /**
     * 新增主表数据
     * @param depothead
     * @return
     */
    boolean addDepotHead(Depothead depothead);

    /**
     * 修改数据
     * @param depothead
     * @return
     */
    boolean updateDepotHead(Depothead depothead);

    /**
     * 审批
     * @param depothead 1:已审核 0:未审核 2：不通过 默认 0 其他数值为待审核
     * @return
     */
    int depotHeadExamin(Depothead depothead);

    /**
     * 标记删除
     * @param depothead 1 删除 0 未删除 默认 0
     * @return
     */
    boolean deleteDepotHead(Depothead depothead);

    /**
     * 报表查询
     * @return
     */
    List<HashMap> queryHead();

}
