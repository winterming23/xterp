package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.winter.Application;
import com.sxt.sys.service.winter.ApplicationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
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
        List<Application> applicationList = applicationService.getAllApplication();
        model.addAttribute("applicationList",applicationList);
        return "system/winter/work/getAllApplication";
    }
}
