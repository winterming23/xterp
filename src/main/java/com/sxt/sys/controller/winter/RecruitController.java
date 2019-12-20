package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.winter.Recruit;
import com.sxt.sys.service.winter.RecruitServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "updateRecruit";
    }
    /**
     * 添加招聘数据
     * @param recruit
     * @return
     */
    @RequestMapping("/saveRecruit")
    @ResponseBody
    public boolean saveRecruit(Recruit recruit){
        boolean flag = recruitService.saveRecruit(recruit);
        return flag;
    }

    /**
     *修改招聘数据
     * @param recruit
     * @return
     */
    @RequestMapping("/updateRecruit")
    @ResponseBody
    public boolean updateRecruit(Recruit recruit){
        boolean flag = recruitService.updateRecruit(recruit);
        return flag;
    }

    /**
     * 删除招聘数据
     * @param deleteFlag
     * @param recruitId
     * @return
     */
    @RequestMapping("/deleteRecruit")
    @ResponseBody
    public boolean deleteRecruit(int deleteFlag, int recruitId){
        boolean flag = recruitService.deleteRecruit(deleteFlag, recruitId);
        return flag;
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
     * 根据编号查询单条招聘信息
     * @param recruitId
     * @return
     */
    @RequestMapping("/findRecruit")
    public String findRecruitOne(int recruitId,Model model){
        Recruit recruitOne = recruitService.findRecruitOne(recruitId);
        model.addAttribute("recruit",recruitOne);
        return "system/winter/recruit/updateRecruit";
    }
}
