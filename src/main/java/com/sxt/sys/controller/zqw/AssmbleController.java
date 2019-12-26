package com.sxt.sys.controller.zqw;



import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.zqw.Assemble;
import com.sxt.sys.domain.zqw.SysUser;
import com.sxt.sys.service.zqw.AssembleServiceI;
import com.sxt.sys.service.zqw.ProductionplanServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
public class AssmbleController {
    @Resource
    private AssembleServiceI assembleServiceI;
    @Resource
    private ProductionplanServiceI productionplanServiceI;
    /**
     * 根据id查询生产名称
     * @param id
     * @return
     */
    @RequestMapping("selePri")
    public String selePri(int id, HttpServletRequest request){
        List<HashMap> list = assembleServiceI.seleProi(id);
        List<SysUser> seleuser = productionplanServiceI.seleuser();
        request.setAttribute("selePri",list);
        request.setAttribute("seleuser",seleuser);
        return "zqw/inserAssemble";
    }

    /**
     * 根据id查询单据数据
     * @param id
     * @param request
     * @return
     */
    @RequestMapping("seledepi")
    public String seledepi(int id, HttpServletRequest request){
        List<HashMap> list1 = assembleServiceI.seleDep(id);
        request.setAttribute("seleDep",list1);
        System.out.println(list1);
        return "zqw/seledeph";
    }

    /**
     * 添加工序
     * @param workingProcedure
     * @param prpersonnel
     * @param productionWorkshop
     * @param assembyTime
     * @param assembyendTime
     * @return
     */
    @RequestMapping("inserAssbble")
    public String inserAssbble(String workingProcedure, Integer prpersonnel, String productionWorkshop, Date assembyTime,Date assembyendTime,int proid){
        System.out.println("++++++++++++++:"+workingProcedure+""+prpersonnel+""+productionWorkshop+""+assembyTime+""+assembyendTime);
        Assemble assemble = new Assemble(0,workingProcedure,prpersonnel,productionWorkshop,assembyTime,assembyendTime,0,proid);
       boolean a = assembleServiceI.inserAssemble(assemble);
        return "redirect:/seleDw";
    }

    /**
     * 查询组装信息
     * @param request
     * @return
     */
    @RequestMapping("seleAssmble")
    public String seleAssmble(HttpServletRequest request){
        List<HashMap> list = assembleServiceI.seleAssem();
        request.setAttribute("seleAssmble",list);
        return "zqw/seleAssemble";
    }

    /**
     * 质检通过
     * @param id
     * @return
     */
    @RequestMapping("AssmnbleSh")
    public String AssmnbleSh(int id){
        boolean b = assembleServiceI.AssemSh(id, 1);
        return "redirect:/seleAssmble";
    }
    /**
     * 质检不通过
     * @param id
     * @return
     */
    @RequestMapping("AssmnbleShNo")
    public String AssmnbleShNo(int id){
        boolean b = assembleServiceI.AssemSh(id, 2);
        return "redirect:/seleAssmble";
    }

    @RequestMapping("Assmrk")
    public String Assmrk(int id,int prpersonnel,Double retail_price,int quantity){
        String verificationCode = String.valueOf((int)((Math.random()*9+1)*1000));
        Date time = new Date();
        Depothead depothead = new Depothead(0,"成品入库",(id+"-"+verificationCode),"0",time,null,0,0,prpersonnel,retail_price,(retail_price*quantity),"0","0",0,0,"0",1008,quantity);
        boolean b = assembleServiceI.inserDepths(depothead);
        return "redirect:/seleAssmble";
    }
}
