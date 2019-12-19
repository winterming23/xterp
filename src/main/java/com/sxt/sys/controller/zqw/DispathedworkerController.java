package com.sxt.sys.controller.zqw;

import com.sxt.sys.service.zqw.ProductionplanServiceI;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class DispathedworkerController {
    @Resource
    private ProductionplanServiceI productionplanServiceI;

}
