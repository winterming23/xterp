package com.sxt.sys.mapper.winter;

import com.sxt.sys.domain.winter.WorkAttendance;
import org.apache.ibatis.annotations.Param;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/6 15:37
 * 考勤数据处理接口
 */
public interface WorkAttendanceMapper {
    /**
     * 修改考勤数据
     * @param workAttendance
     * @return
     */
    boolean updateWorkAttendance(WorkAttendance workAttendance);

    /**
     * 添加考勤
     * @param workAttendance
     * @return
     */
    boolean saveWorkAttendance(WorkAttendance workAttendance);

    /**
     * 修改考勤状态
     * @param state
     * @param id
     * @return
     */
    boolean updateWorkAttendanceState(@Param("state") int state,
                                      @Param("id") int id);


    /**
     * 修改上班时间
     * @param startTime
     * @param id
     * @return
     */
    boolean updateWorkStartTime(@Param("startTime") Time startTime, int id);
    /**
     * 修改下班时间
     * @param endTime 结束时间
     * @param allHour 总时长
     * @param id 编号
     * @return
     */
    boolean updateWorkAttendanceDate(@Param("startTime") Time startTime,
                                     @Param("endTime") Time endTime,
                                     @Param("allHour") Time allHour,
                                     @Param("id") int id);

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
