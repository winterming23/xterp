package com.sxt.sys.controller.winter;

import com.sxt.sys.domain.winter.Leave;
import com.sxt.sys.service.winter.LeaveServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/11 15:46
 */
@Controller
public class LeaveController {
    @Autowired
    private LeaveServiceI leaveService;

    /**
     * 添加请假数据
     * @param leave
     * @return
     */
    @RequestMapping("/addLeave")
    @ResponseBody
    public boolean saveLeave(Leave leave){
        boolean flag = leaveService.saveLeave(leave);
        return flag;
    }

    /**
     * 修改请假数据
     * @param leave
     * @return
     */
    @RequestMapping("/updateLeave")
    public String updateLeave(Leave leave){
        boolean flag = leaveService.updateLeave(leave);
        if (flag){
            return "/getLeave";
        }else {
            return "false";
        }
    }

    /**
     * 根据编号修改状态
     * 用于审核是否通过请假
     * @param leaveId
     * @param verifyStatus
     * @return
     */
    @RequestMapping("/updateLeaveStatus")
    @ResponseBody
    public boolean updateLeaveStatus( int leaveId, int verifyStatus){
        boolean flag = leaveService.updateLeaveStatus(leaveId,verifyStatus);
        return flag;
    }

    /**
     * 根据编号删除请假数据
     * @param deleteFlag
     * @param leaveId
     * @return
     */
    @RequestMapping("/deleteLeave")
    @ResponseBody
    public boolean deleteLeave(int deleteFlag,int leaveId){
        boolean flag = leaveService.deleteLeave(deleteFlag,leaveId);
        return flag;
    }

    /**
     * 未被删除的请假数据
     * @param model 
     * @return
     */
    @RequestMapping("/getLeave")
    public String getAllNoDeleteLeave(Model model){
        List<HashMap> allNoDeleteLeave = leaveService.getAllNoDeleteLeave();
        model.addAttribute("noDeleteLeave",allNoDeleteLeave);
        return "system/winter/leave/noDeleteLeave";
    }

    /**
     * 已被删除的请假数据
     * @return
     */
    public String getAllDeleteLeave(Model model){
        List<Leave> deleteLeave = leaveService.getAllDeleteLeave();
        model.addAttribute("deleteLeave",deleteLeave);
        return null;
    }

    /**
     * 所有请假数据
     * @return
     */
    public String getAllLeave(Model model){
        List<Leave> allLeave = leaveService.getAllLeave();
        model.addAttribute("allLeave",allLeave);
        return null;
    }

    /**
     * 根据编号查询单条请假数据
     * @param leaveId
     * @return
     */
    @RequestMapping("/findLeave")
    public String findLeaveOne(int leaveId, Model model){
        Leave leaveOne = leaveService.findLeaveOne(leaveId);
        model.addAttribute("leaveOne",leaveOne);
        return "system/winter/leave/updateLeave";
    }

    /**
     * 跳转到添加页面
     * @return
     */
    @RequestMapping("/getSaveLeave")
    public String getSaveLeave(){
        return "system/winter/leave/saveLeave";
    }
}
