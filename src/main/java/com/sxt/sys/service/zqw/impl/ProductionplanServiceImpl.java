package com.sxt.sys.service.zqw.impl;

import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;
import com.sxt.sys.domain.zqw.Userinfo;
import com.sxt.sys.mapper.zqw.ProductionplanMapper;
import com.sxt.sys.service.zqw.ProductionplanServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 生产计划业务实现类
 */
@Service
public class ProductionplanServiceImpl implements ProductionplanServiceI {
    @Autowired
    private ProductionplanMapper productionplanMapper;
    /**
     * 查询生产计划
     * @return
     */
    @Override
    public List<HashMap> seleProuct() {
        return productionplanMapper.seleProuct();
    }

    /**
     * 添加一个生产计划
     * @param productionplan
     * @return
     */
    @Override
    public boolean inserProuct(Productionplan productionplan) {
        return productionplanMapper.inserProuct(productionplan);
    }

    /**
     * 修改生产计划
     * @param productionplan
     * @return
     */
    @Override
    public boolean updatePeouct(Productionplan productionplan) {
        return productionplanMapper.updatePeouct(productionplan);
    }

    /**
     * 删除生产计划
     * @param id
     * @return
     */
    @Override
    public boolean deletePeouct(int deleteProd,int id) {
        return productionplanMapper.deletePeouct(deleteProd,id);
    }

    /**
     * /根生产表查询产品信息
     * @return
     */
    @Override
    public List<HashMap> dgselepro() {
        return productionplanMapper.dgselepro();
    }

    /**
     * 查询产品信息
     * @return
     */
    @Override
    public List<HashMap> selepm() {
        return productionplanMapper.selepm();
    }

    /**
     * 添加领料信息
     * @param picking
     * @return
     */
    @Override
    public boolean inserpick(Picking picking) {
        return productionplanMapper.inserpick(picking);
    }

    @Override
    public List<Product_model> selepmll() {
        return productionplanMapper.selepmll();
    }

    /**
     * 查询用户姓名
     * @return
     */
    @Override
    public List<Userinfo> seleuser() {
        return productionplanMapper.seleuser();
    }

    /**
     * 根据id查询所需物料
     * @param pickingid
     * @return
     */
    @Override
    public List<HashMap> selepropick(int pickingid) {
        return productionplanMapper.selepropick(pickingid);
    }

    @Override
    public List<HashMap> seleProckll() {
        return productionplanMapper.seleProckll();
    }


}
