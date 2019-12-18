package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.winter.Recruit;
import com.sxt.sys.service.winter.RecruitServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/6 15:06
 */
@Controller
public class RecruitController {
    private Logger logger = LoggerFactory.getLogger(RecruitController.class);
    @Autowired
    private RecruitServiceI recruitService;

    /**
     * 跳转至添加页面
     * @return
     */
    @RequestMapping("/addRecruit")
    public String addRecruit(){
        return "system/winter/recruit/saveRecruit";
    }
    /**
     * 添加招聘数据
     * @param recruit
     * @return
     */
    @RequestMapping("/saveRecruit")
    public String saveRecruit(Recruit recruit){
        boolean flag = recruitService.saveRecruit(recruit);
        if (flag){
            return "forward:/getRecruit";
        }else{
            return "false";
        }
    }

    /**
     *修改招聘数据
     * @param recruit
     * @return
     */
    @RequestMapping("/updateRecruit")
    public String updateRecruit(Recruit recruit){
        boolean flag = recruitService.updateRecruit(recruit);
        if (flag){
            return "/getRecruit";
        }else {
            return "false";
        }
    }

    /**
     * 删除招聘数据
     * @param deleteFlag
     * @param recruitId
     * @return
     */
    @RequestMapping("/deleteRecruit")
    public String deleteRecruit(int deleteFlag, int recruitId){
        boolean flag = recruitService.deleteRecruit(deleteFlag, recruitId);
        if (flag){
            return "/getRecruit";
        }else{
            return "false";
        }
    }

    /**
     * 查看所有未删除的数据
     * @param model
     * @return
     */
    @RequestMapping("/getRecruit")
    public String getAllNoDeleteRecruit(Model model){
        List<Recruit> allNoDeleteRecruit = recruitService.getAllNoDeleteRecruit();
        model.addAttribute("noDeleteRecruit",allNoDeleteRecruit);
        return "system/winter/recruit/noDeleteRecruit";
    }

    /**
     * 查看所有已删除数据
     * @param model
     * @return
     */
    @RequestMapping("/getAllDeleteRecruit")
    public String getAllDeleteRecruit(Model model){
        List<Recruit> allDeleteRecruit = recruitService.getAllDeleteRecruit();
        model.addAttribute("deleteRecruit",allDeleteRecruit);
        return "";
    }

    /**
     * 查询所有招聘信息
     * @param model
     * @return
     */
    @RequestMapping("getAllRecruit")
    public String getAllRecruit(Model model){
        List<Recruit> allRecruit = recruitService.getAllRecruit();
        model.addAttribute("allRecruit",allRecruit);
        return "";
    }

    /**
     * 根据编号查询单条招聘信息
     * @param recruitId
     * @return
     */
    @RequestMapping("/findRecruit")
    public String findRecruitOne(int recruitId, Model model){
        Recruit recruitOne = recruitService.findRecruitOne(recruitId);
        model.addAttribute("recruit",recruitOne);
        return "";
    }
}
