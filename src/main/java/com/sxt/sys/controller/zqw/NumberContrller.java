package com.sxt.sys.controller.zqw;

import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.zqw.Number;
import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.domain.zqw.Productionplan;
import com.sxt.sys.service.zqw.NumberServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Controller
public class NumberContrller {
    @Resource
    private NumberServiceI numberServiceI;

    /**
     * 添加领料
     * @param
     * @return
     */
    @RequestMapping("insernum")
    public String insernum(int id, Date startTime, Date endTime, int personCharge, String[] numbersl, int materialsId, HttpServletRequest request) throws ParseException {
     Date t = new Date();

        List<Materials> materials =numberServiceI.seleMat();
            Productionplan productionplan = new Productionplan(0, id, startTime, endTime, personCharge, 0, 0, materialsId);
            for (int i=0;i<numbersl.length;i++){
                Number number = new Number(0, materialsId, numbersl[i], materialsId);
                Boolean aBoolean = numberServiceI.inserNum(number, new Picking(0, materialsId, t, 0, 0), productionplan);
            }
            return "redirect:/pdsele";
    }
}
