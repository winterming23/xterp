package com.sxt.sys.service.zqw;



import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 物料数量的业务层操作接口
 */
public interface NumberServiceI {
    /**
     * 添加物料数量
     * @param
     * @return
     */
    Boolean inserNum(int id, String startTime, String endTime, int personCharge, Integer[] numbersl, int[] materialsId, String[] catname, Integer quantity, Integer salesid) throws ParseException;

    /**
     * 查询材料表信息
     * @return
     */
    List<Materials> seleMat();

    /**
     * 查询领料信息
     * @return
     */
    List<Picking> selepicking();

    /**
     * 查询生产计划
     * @return
     */
    List<Productionplan> seleProduct();

    /**
     * 根据根据子表和生产查询id
     * @return
     */
    List<HashMap> selePadd();
}
