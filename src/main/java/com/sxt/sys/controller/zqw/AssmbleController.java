package com.sxt.sys.controller.zqw;



import com.sxt.sys.domain.qxs.warehouse.Depothead;
import com.sxt.sys.domain.zqw.Assemble;
import com.sxt.sys.domain.zqw.SysUser;
import com.sxt.sys.service.zqw.AssembleServiceI;
import com.sxt.sys.service.zqw.DispatchedworkerServiceI;
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
public class AssmbleController {
    @Resource
    private AssembleServiceI assembleServiceI;
    @Resource
    private ProductionplanServiceI productionplanServiceI;
    @Resource
    private DispatchedworkerServiceI dispatchedworkerServiceI;
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
        dispatchedworkerServiceI.dispathSha(id, 4);
        return "system/zqw/inserAssemble";
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
        List<HashMap> list = assembleServiceI.seleDepqb(id);
        List<HashMap> list2 = assembleServiceI.seleDepqba(id);
        request.setAttribute("seleDep",list1);
        request.setAttribute("seleDepqb",list);
        request.setAttribute("seleDepqba",list2);
        System.out.println(list1);
        return "system/zqw/seledeph";
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
    public String inserAssbble(String workingProcedure, Integer prpersonnel, String productionWorkshop, String assembyTime,String assembyendTime,int proid) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sdf.parse(assembyTime);
        Date date2 = sdf.parse(assembyendTime);
        System.out.println("++++++++++++++:"+workingProcedure+""+prpersonnel+""+productionWorkshop+""+assembyTime+""+assembyendTime);
        Assemble assemble = new Assemble(0,workingProcedure,prpersonnel,productionWorkshop,date1,date2,0,proid);
       boolean a = assembleServiceI.inserAssemble(assemble);
        return "redirect:/seleAssmble";
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
        return "system/zqw/seleAssemble";
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

    /**
     * 添加成品入库单
     * @param id
     * @param prpersonnel
     * @param retail_price
     * @param quantity
     * @return
     */
    @RequestMapping("Assmrk")
    public String Assmrk(int id,int prpersonnel,Double retail_price,int quantity){
        String verificationCode = String.valueOf((int)((Math.random()*9+1)*1000));
        Date time = new Date();
        Depothead depothead = new Depothead(0,"成品入库",(id+"-"+verificationCode),"0",time,null,0,prpersonnel,0,retail_price,(retail_price*quantity),"0","0",0,0,"0",1009,quantity);
        boolean b = assembleServiceI.inserDepths(depothead);
        boolean b1 = assembleServiceI.AssemSh(id, 4);
        return "redirect:/seleAssmble";
    }
}
