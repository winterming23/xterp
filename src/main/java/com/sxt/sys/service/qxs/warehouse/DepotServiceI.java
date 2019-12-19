package com.sxt.sys.service.qxs.warehouse;

import com.sxt.sys.domain.qxs.warehouse.Depot;

import java.util.List;

/**
 * 仓库业务实现
 */
public interface DepotServiceI {
    /**
     * 查询所有未删除的仓库
     * @return
     */
    List<Depot> queryNotDeleteDepot();

    /**
     * 查询所有数据包括删除的数据
     * @return
     */
    List<Depot> queryAllDepot();

    /**
     * 查询所有被删除的仓库
     * @return
     */
    List<Depot> queryDelete();

    /**
     * 根据仓库名查询
     * @param name
     * @return
     */
    Depot getDepotName(String name);

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    Depot getOneDepot(Integer id);

    /**
     * 修改仓库信息
     * @param depot
     * @return
     */
    boolean updateDepot(Depot depot);

    /**
     * 仓库默认设置
     * @param isDefault 0 默认 1 不默认；默认为1
     * @return
     */
    boolean defaultDepot(Integer isDefault, Integer id);

    /**
     * 标记删除
     * @param deleteFlag 0 未删除；1 已删除 默认 0
     * @param id 删除条件
     * @return
     */
    boolean deleteDepot(String deleteFlag, Integer id);

    /**
     * 新增仓库
     * @param depot
     * @return
     */
    boolean addDepot(Depot depot);
}
