package com.sxt.sys.service.zqw.impl;

import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.zqw.Number;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;
import com.sxt.sys.mapper.zqw.NumberMapper;
import com.sxt.sys.mapper.zqw.ProductionplanMapper;
import com.sxt.sys.service.zqw.NumberServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 所需物料数量业务实现类
 */
@Service
public class NumberServiceImpl implements NumberServiceI {
    @Autowired
    private NumberMapper numberMapper;
    @Autowired
    private ProductionplanMapper productionplanMapper;


    @Override
    public Boolean inserNum(Number number, Picking picking, Productionplan productionplan) {

        Boolean aBoolean = numberMapper.inserNum(number);
        System.out.println(picking.getStartTime()+"--------------------------->sj");
        boolean inserpick = productionplanMapper.inserpick(picking);
        boolean b = productionplanMapper.inserProuct(productionplan);
        if (aBoolean && inserpick && b){
            return true;
        }
        return false;
    }

    @Override
    public List<Materials> seleMat() {
        return numberMapper.seleMat();
    }

    @Override
    public List<Picking> selepicking() {
        return numberMapper.selepicking();
    }

    @Override
    public List<Productionplan> seleProduct() {
        return numberMapper.seleProduct();
    }

    /**
     * 根据根据子表和生产查询id
     * @return
     */
    @Override
    public List<HashMap> selePadd() {
        return numberMapper.selePadd();
    }

}
