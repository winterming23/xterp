package com.sxt.sys.service.zqw;

import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.winter.ApplyFor;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;
import com.sxt.sys.domain.zqw.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 生产计划业务操作接口
 */
public interface ProductionplanServiceI {
    /**
     * 查询生产计划
     * @return
     */
    List<HashMap> seleProuct();
    /**
     * 添加一个生产计划
     * @param productionplan
     * @return
     */
    boolean inserProuct(Productionplan productionplan);

    /**
     * 修改生产计划
     * @param productionplan
     * @return
     */
    boolean updatePeouct(Productionplan productionplan);

    /**
     * 根据id删除生产计划
     * @param id
     * @return
     */
    boolean deletePeouct(@Param("deleteProd") int deleteProd, @Param("id") int id);
    /**
     * 根据申请表查询产品名称
     * @return
     */
    List<HashMap> dgselepro(int productId);

    /**
     * 查询产品信息
     * @return
     */
    List<HashMap> selepm();

    /**
     * 添加领料信息
     * @param picking
     * @return
     */
    boolean inserpick(Picking picking);

    /**
     * 查询产品信息
     * @return
     */
    List<Product_model> selepmll();

    /**
     * 查询用户姓名
     * @return
     */
    List<SysUser> seleuser();

    /**
     * 根据id查询所需物料
     * @param pickingid
     * @return
     */
    List<HashMap> selepropick(int pickingid);

    /**
     * 查询生产和领料的数据
     * @return
     */
    List<HashMap> seleProckll();

    /**
     * 修改审核状态
     * @param id
     * @param productionAudit
     * @return
     */
    boolean productSh(@Param("id") int id, @Param("productionAudit") int productionAudit);
    /**
     * 根据id查询生产名称
     * @param
     * @return
     */
    List<SysUser> seleusers();

    /**
     * 查询申请数据
     * @return
     */
    List<HashMap> seleapply();
    /**
     * 审核
     * @param auditor
     * @param salesStatus
     * @return
     */
    boolean applySh(@Param("auditor") int auditor, @Param("salesStatus") int salesStatus);

    /**
     * 根据id查询申请数据
     * @param auditor
     * @return
     */
    List<ApplyFor> seleappfor(int auditor);
}
