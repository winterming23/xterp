package com.sxt.sys.service.qxs.warehouse;

import com.sxt.sys.domain.qxs.warehouse.DepotItem;

import java.util.HashMap;
import java.util.List;

/**
 * 单据子表业务操作
 */
public interface DepotItemServiceI {

    /**
     * 查询所有未删除的单据子表
     * @return
     */
    List<DepotItem> queryNotDeleteDepotItem();

    /**
     * 查询所有数据包括已删除的数据
     * @return
     */
    List<HashMap> queryAllDepotItem();

    /**
     * 根据depotHead Id 进行查询
     * @param id
     * @return
     */
    DepotItem getOnDepotHeadID(Integer id);

    /**
     * 查询被删除的数据
     * @return
     */
    List<DepotItem> queryDelete();

    /**
     * 根据depotItem id进行查询
     * @param id
     * @return
     */
    DepotItem getOneDepotItem(Integer id);

    /**
     * 新增子单据
     * @param depotItem
     * @return
     */
    boolean addDepotItem(DepotItem depotItem);

    /**
     * 修改单据子表数据
     * @param depotItem
     * @return
     */
    boolean updateDepotItem(DepotItem depotItem);

    /**
     *  标记删除
     * @param flag 1：删除，0：不删除
     * @param id 条件
     * @param depotItem
     * @return
     */
    boolean deleteFlagDepotItem(DepotItem depotItem);

    /**
     * 查询物品是否存在
     * 存在则行数量修改，不存在进行添加
     * @param materialId
     * @return
     */
    DepotItem queryDepotItemRecord(Integer materialId);

    /**
     * 修改数量
     * @param amount
     * @param materialID
     * @param depotItem
     * @return
     */
    boolean updateAmount(DepotItem depotItem);

    /**
     * 上传图片
     * @param img
     * @param id
     * @return
     */
    boolean updateImg(String img,Integer id);
}
