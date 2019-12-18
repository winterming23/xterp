package com.sxt.sys.controller.zqw;


import com.sxt.sys.domain.vin.Product_model;
import com.sxt.sys.domain.zqw.Productionplan;
import com.sxt.sys.domain.zqw.Userinfo;
import com.sxt.sys.service.zqw.ProductionplanServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProductionplanContrller {
    @Resource
    private ProductionplanServiceI productionplanServiceI;

    /**
     * 查询所有生产计划
     * @param request
     * @return
     */
    @RequestMapping("pdsele")
    public String pdsele(HttpServletRequest request){
        List<HashMap> list = productionplanServiceI.seleProuct();
        request.setAttribute("pdsele",list);
        System.out.println(list);
        return "system/zqw/seleProuct";
    }
    @RequestMapping("aaa")
    public String aaa(){
        return "system/zqw/pickingTj";
    }
    /**
     * 根据申请表查询产品表
     * @param request
     * @return
     */
    @RequestMapping("dgselepd")
   public String dgselepd(HttpServletRequest request){
        List<HashMap> list = productionplanServiceI.dgselepro();
        List<Userinfo> seleuser = productionplanServiceI.seleuser();
        request.setAttribute("dgselepd",list);
        request.setAttribute("seleuserf",seleuser);
        System.out.println(list);
        return "system/zqw/index";
   }

    /**
     * 查询产品信息
     * @param request
     * @return
     */
   @RequestMapping("selepml")
    public String selepml(HttpServletRequest request){
       List<Product_model> selepmll = productionplanServiceI.selepmll();
       request.setAttribute("selepmll",selepmll);
       System.out.println(selepmll);
       return "system/zqw/pickingTj";
    }


    /**
     * 添加生产计划
     * @param id
     * @param startTime
     * @param endTime
     * @param personCharge
     * @param prquantity
     * @return
     */
   @RequestMapping("inserPro")
    public String inserPro(int id, String startTime, String endTime, int personCharge, int prquantity, HttpServletRequest request) throws ParseException {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date date1 = sdf.parse(startTime);
       Date date2 = sdf.parse(endTime);
       Productionplan productionplan = new Productionplan(0,id,date1,date2,personCharge,0,0,prquantity);
       boolean b = productionplanServiceI.inserProuct(productionplan);
       System.out.println(b);
       return "redirect:/pdsele";
    }

    /**
     * 删除生产计划
     * @param id
     * @param
     * @return
     */
    @RequestMapping("delepro")
    public String delepro(int id){
        boolean b = productionplanServiceI.deletePeouct(2,id);
        return "redirect:/pdsele";
    }

    /**
     * 根据id查询所需物料
     * @param pickingid
     * @return
     */
    @RequestMapping("selepropick")
    public String selepropick(int pickingid, HttpServletRequest request){
        List<HashMap> selepropick = productionplanServiceI.selepropick(pickingid);
        request.setAttribute("selepropick",selepropick);
        return "system/zqw/selepropick";
    }

}
