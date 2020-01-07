package com.sxt.sys.controller.zqw;


import com.sxt.sys.service.zqw.NumberServiceI;
import com.sxt.sys.service.zqw.impl.PickingServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;


@Controller
public class NumberContrller {
    @Resource
    private NumberServiceI numberServiceI;
    @Resource
    private PickingServiceImpl pickingService;
    /**
     * 添加领料
     * @param
     * @return
     */
    @RequestMapping("insernum")
    public String insernum(int id, String startTime, String endTime, int personCharge, Integer[] numbersl, int[] materialsId, String[] catname, Integer quantity, Integer salesid, int seleappforid,HttpServletRequest request) throws ParseException {
            numberServiceI.inserNum(id,startTime,endTime,personCharge,numbersl,materialsId,catname,quantity,salesid,seleappforid);
            boolean b = pickingService.pickingSh(id, 4);
        return "redirect:/pdsele";
    }
}
