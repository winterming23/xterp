package com.sxt.sys.service.zqw;


import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.zqw.Dispatchedworker;
import com.sxt.sys.domain.zqw.Number;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

public interface DispatchedworkerServiceI {
    /**
     * 查询所有派工信息
     * @return
     */
    List<HashMap> seleDw();

    /**
     * 添加派工信息
     * @param dispatchedworker
     * @return
     */
    boolean inserDw(Dispatchedworker dispatchedworker);

    /**
     * 修改派工信息
     * @param dispatchedworker
     * @return
     */
    boolean updateDw(Dispatchedworker dispatchedworker);

    /**
     * 根据id删除派工信息
     * @param id
     * @return
     */
    boolean deleltDw(int id);

    /**
     * 添加仓库单据主表
     * @param
     * @return
     */
    boolean inserDepth(Integer pickingid, Integer handsPersonId, Double[] changeAmount, Integer[] numbersl, String remark, Integer[] materialsId);
    /**
     * 根据id查询领料数据
     * @param id
     * @return
     */
    List<HashMap> seleppik(int id);
    /**
     * 修改派工的审核状态
     * @param id
     * @param dispatchedAudits
     * @return
     */
    boolean dispathSh(@Param("id") int id, @Param("dispatchedAudits") int dispatchedAudits, @Param("pickingid") int pickingid, @Param("dispatchedNo") int dispatchedNo,@Param("salesid")int salesid);
    /**
     * 根据id查询派工状态
     * @param id
     * @return
     */
    List<Dispatchedworker> selediswork(int id);
    /**
     * 根据生产编号查询数量信息
     * @param pickingid
     * @return
     */
    List<Number> seleDisNumber(int pickingid);

    /**
     * 根据单据子表id查询数据
     * @param id
     * @return
     */
    List<Materials> seleMater(int id);
    /**
     * 根据id查询派工信息
     * @param id
     * @return
     */
    List<Dispatchedworker> seleDiskker(int id);
    /**
     * 修改派工的审核状态
     * @param id
     * @param dispatchedAudits
     * @return
     */
    boolean dispathSha(@Param("id") int id, @Param("dispatchedAudits") int dispatchedAudits);

}
