package com.sxt.sys.controller.zqw;


import com.sxt.sys.domain.zqw.Picking;
import com.sxt.sys.service.zqw.PickingServiceI;
import com.sxt.sys.service.zqw.ProductionplanServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class PickingContrller {
    @Resource
    private PickingServiceI pickingServiceI;
    @Resource
    private ProductionplanServiceI productionplanServiceI;
    /**
     * 查询领料信息
     * @param request
     * @return
     */
    @RequestMapping("selePickadd")
    public String selePickadd(HttpServletRequest request){
        List<HashMap> list = pickingServiceI.selePicking();
        request.setAttribute("selePickadd",list);
        System.out.println(list);
        return "system/zqw/selepicking";
    }

    /**
     * 根据生产id查询领料数据
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("seleDgPick")
    public String seleDgPick(Integer id, HttpServletRequest request){
        List<HashMap> list = pickingServiceI.seleDgpick(id);
        List<HashMap> list1 = pickingServiceI.seleDguser(id);
        List<HashMap> list2 = pickingServiceI.seleDgProduct(id);
        request.setAttribute("seleDgPick",list);
        request.setAttribute("seleDguser",list1);
        request.setAttribute("seleDgProduct",list2);
        productionplanServiceI.productSh(id, 4);
        System.out.println(list);
        return "system/zqw/Pickadd";
    }

    /**
     * 添加领料信息
     * @param productionid
     * @return
     */
    @RequestMapping("inserPickingd")
    public String inserPickingd(Integer productionid,Integer pickingNo){
        Date t = new Date();
        Picking picking = new Picking(0,pickingNo,t,0,0,productionid);
        pickingServiceI.inserPicking(picking);
        return "redirect:/selePickadd";
    }

    /**
     * 领料审核
     * @param id
     * @return
     */
    @RequestMapping("pcikSh")
    public String pcikSh(int id){
        boolean b = pickingServiceI.pickingSh(id, 1);
        return "redirect:/selePickadd";
    }

    /**
     * 审核未通过
     * @param id
     * @return
     */
    @RequestMapping("pcikShNo")
    public String pcikShNo(int id){
        boolean b = pickingServiceI.pickingSh(id, 1);
        return "redirect:/selePickadd";
    }
}
