package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.winter.Application;
import com.sxt.sys.service.winter.ApplicationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/20 15:10
 */
@Controller
public class ApplicationController {
    @Autowired
    private ApplicationServiceI applicationService;

    @RequestMapping("/saveApplication")
    @ResponseBody
    public boolean saveApplication(Application application){
        application.setAuditTime(null);
        boolean flag = applicationService.saveApplication(application);
        return flag;
    }


    @RequestMapping("/allApplication")
    public String getAllApplication(Model model){
        List<HashMap> applicationList = applicationService.getAllApplication();
        for (HashMap hashmap:applicationList) {
            System.out.println("hashmap:::::"+hashmap);
        }
        model.addAttribute("applicationList",applicationList);
        return "system/winter/work/getAllApplication";
    }

    /**
     * 修改审核状态
     * @param id
     * @param approval
     * @return
     */
    @RequestMapping("/updateApplicationApproval")
    @ResponseBody
    public boolean updateApplicationApproval(int id, int approval, HttpServletRequest request){
        Object ids = request.getSession().getAttribute("userId");
        int userId = (int)ids;
        boolean flag = applicationService.updateApplicationApproval(approval, id);
        //审核状态为2时 修改审核人和审核时间
        if(approval == 2){
            boolean b = applicationService.updateApplicationAuditTime(new Date(), userId, id);
        }
        return flag;
    }
}
