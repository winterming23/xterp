package com.sxt.sys.domain.winter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Winter
 * @Date 2019/12/2 11:15
 * 请假实体类
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Leave implements Serializable {
    private static final long serialVersionUID = 6483377675890330726L;
    private Integer leaveId;//请假编号
    private Integer userId;//用户编号
    private String reason;//请假原因
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;//开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束时间
    private Integer allHour;//总时长
    private Integer verifyStatus;//审核 0通过 1未通过
    private Integer deleteFlag;//请假编号

}
