package com.sxt.sys.controller.zqw;


import com.sxt.sys.domain.qxs.warehouse.Materials;
import com.sxt.sys.domain.winter.ApplyFor;
import com.sxt.sys.domain.zqw.SysUser;
import com.sxt.sys.service.zqw.NumberServiceI;
import com.sxt.sys.service.zqw.ProductionplanServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProductionplanContrller {
    @Resource
    private ProductionplanServiceI productionplanServiceI;
    @Resource
    private NumberServiceI numberServiceI;
    /**
     * 查询所有生产计划
     * @param request
     * @return
     */
    @RequestMapping("pdsele")
    public String pdsele(HttpServletRequest request){
        List<HashMap> list = productionplanServiceI.seleProuct();
        request.setAttribute("pdsele",list);
        return "system/zqw/seleProuct";
    }
    @RequestMapping("aaa")
    public String aaa(){
        return "zqw/pickingTj";
    }
    /**
     * 根据申请表查询产品表
     * @param request
     * @return
     */
    @RequestMapping("dgselepd")
   public String dgselepd(HttpServletRequest request, int productId, int auditor ){
        List<HashMap> list = productionplanServiceI.dgselepro(productId);
        List<SysUser> seleuser = productionplanServiceI.seleusers();
        List<HashMap> list1 = productionplanServiceI.seleProckll();
        List<Materials> selepmll = numberServiceI.seleMat();
        List<ApplyFor> seleappfor = productionplanServiceI.seleappfor(auditor);
        request.setAttribute("selepmll",selepmll);
        request.setAttribute("dgselepd",list);
        request.setAttribute("seleuserf",seleuser);
        request.setAttribute("seleprockll",list1);
        request.setAttribute("seleappfor",seleappfor);
        return "system/zqw/index1";
   }



    /**
     * 添加生产计划
     * @param id
     * @param startTime
     * @param endTime
     * @param personCharge
     * @param pickingid
     * @return
     */
/*   @RequestMapping("inserPro")
    public String inserPro( int id, String startTime,String endTime,int personCharge,int pickingid,HttpServletRequest request) throws ParseException {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       Date date1 = sdf.parse(startTime);
       Date date2 = sdf.parse(endTime);
       Productionplan productionplan = new Productionplan(0,id,date1,date2,personCharge,0,0,pickingid);
       boolean b = productionplanServiceI.inserProuct(productionplan);
       System.out.println(b);
       return "redirect:/pdsele";
    }*/

    /**
     * 删除生产计划
     * @param id
     * @param
     * @return
     */
    @RequestMapping("delepro")
    public String delepro(int id){
        boolean b = productionplanServiceI.deletePeouct(1,id);
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

    /**
     * 修改生产的审核状态
     * @param id
     * @return
     */
    @RequestMapping("DgaddProduct")
    public String DgaddProduct(int id){
        boolean b = productionplanServiceI.productSh(id, 1);
        return "redirect:/pdsele";
    }

    /**
     * 查询申请数据
     * @param request
     * @return
     */
    @RequestMapping("seleapply")
    public String seleapply(HttpServletRequest request){
        List<HashMap> seleapply = productionplanServiceI.seleapply();
        request.setAttribute("seleapply",seleapply);
        System.out.println(seleapply);
        return "system/zqw/apply";
    }

    /**
     *申请表审核
     * @param auditor
     * @return
     */
    @RequestMapping("applySh")
    public String applySh(int auditor){
        boolean b = productionplanServiceI.applySh(auditor, 1);
        return "redirect:/seleapply";
    }

    /**
     * 申请表审核不通过
     * @param auditor
     * @return
     */
    @RequestMapping("applyShNo")
    public String applyShNo(int auditor){
        boolean b = productionplanServiceI.applySh(auditor, 2);
        return "redirect:/seleapply";
    }
}
