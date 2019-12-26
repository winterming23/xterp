package com.sxt.sys.controller.zqw;

import com.sxt.sys.domain.zqw.Dispatchedworker;
import com.sxt.sys.domain.zqw.SysUser;
import com.sxt.sys.service.zqw.DispatchedworkerServiceI;
import com.sxt.sys.service.zqw.ProductionplanServiceI;
import com.sxt.sys.service.zqw.impl.AssembleServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class DispathedworkerController {
    @Resource
    private ProductionplanServiceI productionplanServiceI;
    @Resource
    private DispatchedworkerServiceI dispatchedworkerServiceI;
    @Resource
    private AssembleServiceImpl assembleService;
    /**
     * 查询派工信息
     * @return
     */
    @RequestMapping("seleDw")
    public String seleDw(HttpServletRequest request){
        List<HashMap> hashMap = dispatchedworkerServiceI.seleDw();
        request.setAttribute("seleDw",hashMap);
        System.out.println();
        return "zqw/seleDw";
    }

    /**
     * 根据id查询领料数据
     * @param request
     * @return
     */
    @RequestMapping("seledapk")
    public String seledapk(int id, HttpServletRequest request){
        List<HashMap> seleppik = dispatchedworkerServiceI.seleppik(id);
        List<SysUser> seleuser = productionplanServiceI.seleuser();
        List<Dispatchedworker> selediswork = dispatchedworkerServiceI.selediswork(id);
        request.setAttribute("seleppik",seleppik);
        request.setAttribute("selepkuser",seleuser);
        request.setAttribute("selediswork",selediswork);
        return "zqw/inserDis";
    }

    /**
     * 添加派工
     * @param pickingid
     * @param handsPersonId
     * @param changeAmount
     * @param numbersl
     * @param remark
     * @param materialsId
     * @return
     */
    @RequestMapping("inserDepothead")
    public String inserDepothead(Integer pickingid,Integer handsPersonId,Double[] changeAmount,Integer[] numbersl,String remark,Integer[] materialsId){
        boolean b = dispatchedworkerServiceI.inserDepth(pickingid, handsPersonId, changeAmount, numbersl, remark, materialsId);
        return "redirect:/seleDw";
    }

    /**
     * 派工审核
     * @param id
     * @return
     */
    @RequestMapping("dispathSh")
    public String dispathSh(int id,int pickingid,int dispatchedNo){
        boolean b = dispatchedworkerServiceI.dispathSh(id, 1, pickingid,dispatchedNo);
        return "redirect:/seleDw";
    }

    /**
     * 审核不通过
     * @param id
     * @param pickingid
     * @param dispatchedNo
     * @return
     */
    @RequestMapping("dispathShNo")
    public String dispathShNo(int id,int pickingid,int dispatchedNo){
        boolean b = dispatchedworkerServiceI.dispathSh(id, 1, pickingid,dispatchedNo);
        return "redirect:/seleDw";
    }

}
