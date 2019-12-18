package com.sxt.sys.domain.winter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * @Author Winter
 * @Date 2019/12/2 15:43
 * 考勤实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkAttendance implements Serializable {
    private Integer id;//主键编号
    private Integer userId;//用户编号
    private Date attendanceDate;//考勤时间
    private String week;//星期几
    private Time startTime;//开始时间
    private Time endTime;//结束时间
    private Time allHour;//总时长
    private Integer state;//考勤状态 0早卡，1工时不足，2全勤，3缺勤,4未打晚卡，5未打早卡
    private Integer deleteFlag;//删除标记
    private Integer workState;//上班状态标识 0上班 1下班
}
