package com.sxt.sys.service.zqw.impl;


import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.zqw.Dispatchedworker;
import com.sxt.sys.domain.zqw.Number;
import com.sxt.sys.mapper.zqw.DispatchedworkerMapper;
import com.sxt.sys.service.zqw.DispatchedworkerServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class DispatchedworkerServiceImpl implements DispatchedworkerServiceI {
    @Autowired
    private DispatchedworkerMapper dispatchedworkerMapper;


    @Override
    public  List<HashMap> seleDw() {
        return dispatchedworkerMapper.seleDw();
    }

    @Override
    public boolean inserDw(Dispatchedworker dispatchedworker) {
        return false;
    }

    @Override
    public boolean updateDw(Dispatchedworker dispatchedworker) {
        return false;
    }

    @Override
    public boolean deleltDw(int id) {
        return false;
    }

    @Override
    public boolean inserDepth(Integer pickingid,Integer handsPersonId,Double[] changeAmount,Integer[] numbersl,String remark,Integer[] materialsId) {
        System.out.println("++++++++++++++++++++++++++++pickingid:"+pickingid+"handsPersonId"+handsPersonId);
        Date time = new Date();
        //生成一个四位的随机数
        String verificationCode = String.valueOf((int)((Math.random()*9+1)*1000));
        List<Dispatchedworker> selediswork = dispatchedworkerMapper.selediswork(pickingid);
        System.out.println("--------"+selediswork.size());
        Dispatchedworker dispatchedworker = new Dispatchedworker(0,handsPersonId,time,0,0,pickingid,(pickingid+"-"+verificationCode));
        boolean b1 = dispatchedworkerMapper.inserDw(dispatchedworker);
        return true;
    }

    @Override
    public List<HashMap> seleppik(int id) {

        return dispatchedworkerMapper.seleppik(id);
    }

    @Override
    public boolean dispathSh(int id, int dispatchedAudits,int pickingid,int dispatchedNo) {
        List<Number> numbers = dispatchedworkerMapper.seleDisNumber(pickingid);
        String verificationCode = String.valueOf((int)((Math.random()*9+1)*1000));
        System.out.println("=============================================="+numbers);
        Date time = new Date();
        List<Dispatchedworker> dispatchedworkers = dispatchedworkerMapper.seleDiskker(id);
        for (Number number : numbers) {
            System.out.println("==================================================1111");
            List<Materials> materials = dispatchedworkerMapper.seleMater(number.getMaterialsId());
            for (Materials materials1:materials) {
                for (Dispatchedworker dispatchedworker:dispatchedworkers) {
                    Depothead depothead1 = new Depothead(0,"零件出库",dispatchedworker.getBillid(),null,time,null,null,dispatchedNo,null,materials1.getPrice(),number.getNumbersl()*materials1.getPrice(),null,null,null,0,null,number.getMaterialsId(),number.getNumbersl());
                    boolean b = dispatchedworkerMapper.inserDepth(depothead1);
                    System.out.println("================================================================"+b);
                }

            }
        }
        boolean b = dispatchedworkerMapper.dispathSh(id, dispatchedAudits);
        return true;
    }

    @Override
    public List<Dispatchedworker> selediswork(int id) {
        return dispatchedworkerMapper.selediswork(id);
    }

    @Override
    public List<Number> seleDisNumber(int pickingid) {
        return dispatchedworkerMapper.seleDisNumber(pickingid);
    }

    @Override
    public List<Materials> seleMater(int id) {
        return dispatchedworkerMapper.seleMater(id);
    }

    @Override
    public List<Dispatchedworker> seleDiskker(int id) {
        return dispatchedworkerMapper.seleDiskker(id);
    }


}
