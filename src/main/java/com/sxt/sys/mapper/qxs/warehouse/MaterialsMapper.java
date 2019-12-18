package com.sxt.sys.mapper.qxs.warehouse;

import com.sxt.sys.domain.qxs.warehouse.Materials;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @program: xterp
 * @description: 材料表数据操作
 * @author: snow
 * @create: 2019-12-16 13:55
 **/
public interface MaterialsMapper {
    /**
     * 查询所有材料类型
     * @return
     */
    @Select("select * from materials")
    List<Materials> queryAllMaterials();

    /**
     * 根据id查询当前数据
     * @param id
     * @return
     */
    @Select("select * from materials where id=#{id}")
    Materials queryOneMaterials(Integer id);

    /**
     *  新增材料类型
     * @param materials
     * @return
     */
    @Update("insert into materials values(null,#{mName},#{remark},#{mType},#{mUnit})")
    boolean addMaterials(Materials materials);
}
