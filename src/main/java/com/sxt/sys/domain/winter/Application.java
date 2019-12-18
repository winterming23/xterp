package com.sxt.sys.domain.winter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Winter
 * @Date 2019/12/9 10:02
 * 考勤申请表
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Application implements Serializable {
    private Integer id;//编号
    private Integer workId;//考勤编号
    private String cause;//申请原因 如忘打卡
    private Integer proposer;//申请人编号
    private Date applicationTime;//申请人时间
    private Integer approval;//审批
    private Integer auditor;//审批人
    private Date auditTime;//审批时间
    private Integer deleteFlag;//标记删除
}
