package com.sxt.sys.service.qxs.warehouse.impl;

import com.sxt.sys.domain.qxs.warehouse.Depot;
import com.sxt.sys.mapper.qxs.warehouse.DepotMapper;
import com.sxt.sys.service.qxs.warehouse.DepotServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: xterp
 * @description: 仓库业务实现
 * @author: snow
 * @create: 2019-12-10 10:08
 **/
@Service
public class DepotServiceImpl implements DepotServiceI {

    @Autowired
    private DepotMapper depotMapper;

    /**
     * 查询所有未删除的仓库
     * @return
     */
    @Override
    public List<Depot> queryNotDeleteDepot() {
        return depotMapper.queryNotDeleteDepot();
    }

    /**
     * 查询所有的仓库
     * @return
     */
    @Override
    public List<Depot> queryAllDepot() {
        return depotMapper.queryAllDepot();
    }

    /**
     * 查询所有已删除的仓库
     * @return
     */
    @Override
    public List<Depot> queryDelete() {
        return depotMapper.queryDelete();
    }

    /**
     * 根据仓库名查询
     * @param name
     * @return
     */
    @Override
    public Depot getDepotName(String name) {
        return depotMapper.getDepotName(name);
    }

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    @Override
    public Depot getOneDepot(Integer id) {
        return depotMapper.getOneDepot(id);
    }

    /**
     * 修改仓库记录
     * @param depot
     * @return
     */
    @Override
    public boolean updateDepot(Depot depot) {
        return depotMapper.updateDepot(depot);
    }

    /**
     * 修改仓库的默认状态
     * @param depot.isDefault 0 默认 1 不默认；默认为1
     * @param depot.id
     * @return
     */
    @Override
    public boolean defaultDepot(Depot depot) {
        return depotMapper.defaultDepot(depot);
    }

    /**
     * 标记删除
     * @param depot.deleteFlag 0 未删除；1 已删除 默认 0
     * @param depot.id 删除条件
     * @return
     */
    @Override
    public boolean deleteDepot(Depot depot) {
        return depotMapper.deleteDepot(depot);
    }

    /**
     * 新增仓库，只允许最高管理员操作
     * @param depot
     * @return
     */
    @Override
    public boolean addDepot(Depot depot) {
        return depotMapper.addDepot(depot);
    }
}
