package com.sxt.sys.service.winter;


import com.sxt.sys.domain.winter.WorkAttendance;

import java.sql.Time;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/9 8:39
 * 考勤业务接口
 */
public interface WorkAttendanceServiceI {
    /**
     * 添加考勤
     * @param workAttendance
     * @return
     */
    boolean saveWorkAttendance(WorkAttendance workAttendance);

    /**
     * 修改考勤数据
     * @param workAttendance
     * @return
     */
    boolean updateWorkAttendance(WorkAttendance workAttendance);
    /**
     * 修改上班时间
     * @param startTime
     * @param id
     * @return
     */
    boolean updateWorkStartTime(Time startTime, int id);

    /**
     * 修改考勤时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param allHour 总时长
     * @param id 编号
     * @return
     */
    boolean updateWorkAttendanceDate(Time startTime, Time endTime, Time allHour, int id) throws ParseException;

    /**
     * 查看该员工的考勤信息
     * @param userId
     * @return
     */
    List<HashMap> getEmployeeAttendance(int userId);

    /**
     * 查询单条员工考勤数据
     * @param id
     * @return
     */
    WorkAttendance findWorkAttendance(int id);
}
