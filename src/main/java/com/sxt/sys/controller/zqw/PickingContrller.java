package com.sxt.sys.controller.zqw;

import com.sxt.sys.service.zqw.PickingServiceI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class PickingContrller {
    @Resource
    private PickingServiceI pickingServiceI;

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
}
