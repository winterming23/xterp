package com.sxt.sys.mapper.vin;

import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.vin.Product_type;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

public interface ProductModelsMapper {
    /**
     * 查询所有产品型号
     * @return
     */
    @Select("SELECT pm.id,pm.brand,pm.model_name,pm.motherboard,pm.cpu,pm.memories,pm.video_card,pm.disks,pm.ssd,pm.size,pm.color,pm.weight,pt.type_name,pm.createtime,pm.status from product_model as pm " +
            " INNER JOIN product_type as pt on pm.model_type = pt.id" +
            " WHERE pm.delete_Flag = '0'")
    public List<HashMap> getAllProductModels();

    /**
     * 新增产品型号
     * @param product_model
     * @return
     */
    @Insert("INSERT INTO product_model (brand,model_name,motherboard,cpu,memories,video_card,disks,ssd,size,color,weight,model_type,delete_Flag,createtime,status)" +
            " VALUES(#{brand},#{model_name},#{motherboard},#{cpu},#{memories},#{video_card},#{disks},#{ssd},#{size},#{color},#{weight},#{model_type},DEFAULT,now(),default)")
    public boolean insertProductModels(Product_model product_model);

    /**
     * 修改产品型号信息
     * @param product_model
     * @return
     */
    @Update("UPDATE product_model SET brand=#{brand},model_name=#{model_name},motherboard =#{motherboard},cpu=#{cpu},memories=#{memories},video_card=#{video_card},disks=#{disks},ssd=#{ssd},size=#{size},color=#{color},weight=#{weight},#{model_type}" +
            " WHERE id = 4")
    public boolean updateProductModels(Product_model product_model);

    /**
     * 修改删除标记
     * @param id
     * @return
     */
    @Update("update product_model set delete_Flag = '1' where id = #{id}")
    public boolean deleteProductModels(long id);

    /**
     * 获取产品类别
     * @return
     */
    @Select("select * from product_type where delete_Flag = '0'")
    public List<Product_type> queryType();

    /**
     * 修改审核状态
     * @param id
     * @return
     */
    @Update("update product_model set status = '1' where id = #{id}")
    public boolean updateProductModelsStatus(long id);
}
