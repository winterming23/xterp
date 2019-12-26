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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    public Boolean inserNum(int id, String startTime, String endTime, int personCharge, Integer[] numbersl, int[] materialsId,String[] catname,Integer quantity,Integer salesid) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(startTime);
        Date date2 = sdf.parse(endTime);
        Number numbers = null;
        for (int i=0;i<numbersl.length;i++){
                numbers = new Number(0, materialsId[i], numbersl[i], id,catname[i]);
                System.out.println("添加的值"+catname[i]);
            numberMapper.inserNum(numbers);
        }
        Date t = new Date();
        Productionplan productionplan = new Productionplan(0, id, date1, date2, personCharge, 0, 0,id ,quantity,salesid);
        boolean b1 = productionplanMapper.inserProuct(productionplan);
        return true;
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
