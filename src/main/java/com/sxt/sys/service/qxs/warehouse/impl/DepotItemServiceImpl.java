package com.sxt.sys.service.qxs.warehouse.impl;

import com.sxt.sys.domain.qxs.warehouse.DepotItem;
import com.sxt.sys.mapper.qxs.warehouse.DepotItemMapper;
import com.sxt.sys.service.qxs.warehouse.DepotItemServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @program: xterp
 * @description: 单据子表业务实现
 * @author: snow
 * @create: 2019-12-10 16:40
 **/
@Service
public class DepotItemServiceImpl implements DepotItemServiceI {

    @Autowired
    private DepotItemMapper depotItemMapper;

    /**
     * 查询所有未删除的记录
     * @return
     */
    @Override
    public List<DepotItem> queryNotDeleteDepotItem() {
        return depotItemMapper.queryNotDeleteDepotItem();
    }

    /**
     * 查询所有记录
     * @return
     */
    @Override
    public List<HashMap> queryAllDepotItem() {
        return depotItemMapper.queryAllDepotItem();
    }

    /**
     *
     * @param id 根据 depotHead 单据主表id进行查询
     * @return
     */
    @Override
    public DepotItem getOnDepotHeadID(Integer id) {
        return depotItemMapper.getOnDepotHeadID(id);
    }

    /**
     * 查询所有已删除的数据
     * @return List<DepotItem>
     */
    @Override
    public List<DepotItem> queryDelete() {
        return depotItemMapper.queryDelete();
    }

    /**
     *
     * @param id 根据单据子表 id 进行查询
     * @return DepotItem
     */
    @Override
    public DepotItem getOneDepotItem(Integer id) {
        return depotItemMapper.getOneDepotItem(id);
    }

    /**
     * 新增 子表数据
     * @param depotItem
     * @return
     */
    @Override
    public boolean addDepotItem(DepotItem depotItem) {
        return depotItemMapper.addDepotItem(depotItem);
    }

    /**
     * 修改子表数据
     * @param depotItem
     * @return
     */
    @Override
    public boolean updateDepotItem(DepotItem depotItem) {
        return depotItemMapper.updateDepotItem(depotItem);
    }

    /**
     * 标记删除
     * @param flag 1：删除，0：不删除
     * @param id 条件
     * @param depotItem
     * @return
     */
    @Override
    public boolean deleteFlagDepotItem(DepotItem depotItem) {
        return depotItemMapper.deleteFlagDepotItem(depotItem);
    }

    /**
     *  查询物品是否存在
     *  存在则行数量修改，不存在进行添加
     * @param materialId
     * @return
     */
    @Override
    public DepotItem queryDepotItemRecord(Integer materialId) {
        return depotItemMapper.queryDepotItemRecord(materialId);
    }

    /**
     * 修改数量
     * @param amount
     * @param materialID
     * @param depotItem
     * @return
     */
    @Override
    public boolean updateAmount(DepotItem depotItem) {

        return depotItemMapper.updateAmount(depotItem);
    }

    @Override
    public boolean updateImg(String img, Integer id) {
        return depotItemMapper.updateImg(img, id);
    }
}
