package com.sxt.sys.controller.winter;

import com.alibaba.fastjson.JSON;
import com.sxt.sys.domain.winter.WorkAttendance;
import com.sxt.sys.service.winter.WorkAttendanceServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Winter
 * @Date 2019/12/12 10:38
 */
@Controller
public class WorkAttendanceController {
    private Logger logger = LoggerFactory.getLogger(WorkAttendanceController.class);

    @Autowired
    private WorkAttendanceServiceI workAttendanceService;

    /**
     * 修改上班时间
     * @param startTime
     * @param id
     * @return
     */
    @RequestMapping("/updateWorkStartTime")
    @ResponseBody
    public boolean updateWorkStartTime(Time startTime, int id){
        System.out.println("startTime："+startTime);
        System.out.println("id"+id);
        boolean flag = workAttendanceService.updateWorkStartTime(startTime, id);
        return flag;
    }
    /**
     * 修改考勤时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param allHour 总时长
     * @param id 编号
     * @return
     */
    @RequestMapping("/updateWorkAttendanceDate")
    @ResponseBody
    public boolean updateWorkAttendanceDate(Time startTime, Time endTime, Time allHour, int id) throws ParseException{
        boolean flag = workAttendanceService.updateWorkAttendanceDate(startTime,endTime,allHour,id);
        return flag;
    }

    /**
     * 查看该员工的考勤信息
     * 表格的 userId
     * @param
     * @param model
     * @return
     */
    @RequestMapping("/employeeAttendance")
    public String getEmployeeAttendance( Model model){
        List<HashMap> employeeAttendance = workAttendanceService.getEmployeeAttendance(1);
        model.addAttribute("employeeAttendance",employeeAttendance);
        return "system/winter/work/workApply";
    }

    /**
     * 跳转到日程页面
     * @return
     */
    @RequestMapping("/workDay")
    public String getWork(){
        return "system/winter/work/workDay";
    }
    /**
     * 查看该员工的考勤信息
     * 日历上的 userId
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/work")
    public List<HashMap> getWorkAttendance(Model model,HttpServletResponse response){
        List<HashMap> employeeAttendance = workAttendanceService.getEmployeeAttendance(1);
        model.addAttribute("employeeAttendance",employeeAttendance);
        String result = JSON.toJSONString(employeeAttendance);
        try {
            sendJsonData(response,result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void sendJsonData(HttpServletResponse response, String param) throws Exception{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(param);
        out.flush();
        out.close();
    }

    /**
     * 查询新增加的数据
     * @param request
     * @return
     */
    @RequestMapping("/findWork")
    public String findWorkAttendance(HttpServletRequest request){
        //获取新增的编号
        //int workId = (int)request.getAttribute("workId");
        WorkAttendance workAttendance = workAttendanceService.findWorkAttendance(4);
        System.out.println("======"+workAttendance);
        request.setAttribute("workUser",workAttendance);
        return "forward:/workDay";
    }
}
