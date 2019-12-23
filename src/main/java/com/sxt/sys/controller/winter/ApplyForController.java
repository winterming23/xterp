package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.winter.ApplyFor;
import com.sxt.sys.service.winter.ApplyForServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Winter
 * @Date 2019/12/20 13:37
 */
@Controller
public class ApplyForController {
    @Autowired
    private ApplyForServiceI applyForService;

    @RequestMapping("/saveApplyFor")
    @ResponseBody
    public boolean saveApplyFor(ApplyFor applyFor){
        boolean flag = applyForService.saveApplyFor(applyFor);
        return flag;
    }
}
