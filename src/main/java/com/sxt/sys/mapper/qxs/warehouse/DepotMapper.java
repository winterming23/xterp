package com.sxt.sys.mapper.qxs.warehouse;

import com.sxt.sys.domain.qxs.warehouse.Depot;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 仓库数据操作
 */
public interface DepotMapper {


    /**
     * 查询所有未删除的仓库
     * @return
     */
    @Select("select * from depot where deleteFlag!=1 order by sort desc")
    List<Depot> queryNotDeleteDepot();

    /**
     * 查询所有数据包括删除的数据
     * @return
     */
    @Select("select * from depot order by sort desc")
    List<Depot> queryAllDepot();

    /**
     * 查询所有被删除的仓库
     * @return
     */
    @Select("select * from depot where deleteFlag=1 order by sort desc")
    List<Depot> queryDelete();

    /**
     * 根据仓库名查询
     * @param name
     * @return
     */
    @Select("select * from depot where name=#{name} and deleteFlag!='1' ")
    Depot getDepotName(String name);

    /**
     * 根据id进行查询
     * @param id
     * @return
     */
    @Select("select * from depot where id=#{id} and deleteFlag!='1' ")
    Depot getOneDepot(Integer id);

    /**
     * 修改仓库信息
     * @param depot
     * @return
     */
    @Update("update depot name=#{name},address=#{address},truckage=#{truckage},type=#{type}," +
            "remark=#{remark},principal=#{principal} where id=#{id} ")
    boolean updateDepot(Depot depot);

    /**
     * 仓库默认设置
     * @param depot.isDefault 0 默认 1 不默认；默认为1
     * @return
     */
    @Update("update depot set isDefault=#{isDefault} where id=#{id}")
    boolean defaultDepot(Depot depot);

    /**
     * 标记删除
     * @param depot.deleteFlag 0 未删除；1 已删除 默认 0
     * @param depot.id 删除条件
     * @return
     */
    @Update("update depot set deleteFlag=#{deleteFlag} where id=#{id}")
    boolean deleteDepot(Depot depot);

    /**
     * 新增仓库
     * @param depot
     * @return
     */
    @Insert("insert into depot values(null,#{name},#{address},#{truckage},#{type},#{sort}," +
            "#{remark},#{principal},0,1)")
    boolean addDepot(Depot depot);
}
