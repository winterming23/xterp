package com.sxt.sys.service.winter;


import com.sxt.sys.domain.winter.Application;

import java.util.Date;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/10 10:12
 * 考勤申请业务操作接口
 */
public interface ApplicationServiceI {
    /**
     * 根据申请人
     * 查询考勤申请数据
     * @param
     * @return
     */
    /*List<Application> getAllApplicationProposer(int proposer);*/

    /**
     * 根据审核人
     * 查询所有的考勤申请数据
     * @param
     * @return
     */
    /*List<Application> getAllApplicationAuditor(int auditor);*/

    /**
     * 查询所有
     * @return
     */
    List<Application> getAllApplication();

    /**
     * 修改考勤审核时间
     * @param auditTime
     * @param id
     * @return
     */
    boolean updateApplicationAuditTime(Date auditTime, int id);

    /**
     * 修改申请审核状态
     * @param approval
     * @param id
     * @return
     */
    boolean updateApplicationApproval(int approval, int id);

    /**
     * 添加考勤信息
     * @param application
     * @return
     */
    boolean saveApplication(Application application);

    /**
     * 删除标记删除考勤信息
     * @param deleteFlag
     * @param id
     * @return
     */
    boolean deleteApplication(int deleteFlag, int id);
}
