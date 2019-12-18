package com.sxt.sys.service.zqw.impl;


import com.sxt.sys.domain.zqw.Number;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.mapper.zqw.NumberMapper;
import com.sxt.sys.mapper.zqw.ProductionplanMapper;
import com.sxt.sys.service.zqw.NumberServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Boolean inserNum(Number number, Picking picking) {
        Boolean aBoolean = numberMapper.inserNum(number);
        boolean inserpick = productionplanMapper.inserpick(picking);
        if (aBoolean && inserpick){
            return true;
        }
        return false;
    }
}
