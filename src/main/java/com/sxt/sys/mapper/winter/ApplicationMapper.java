package com.sxt.sys.mapper.winter;

import com.sxt.sys.domain.winter.Application;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/9 14:29
 * 考勤申请数据操作接口
 */
public interface ApplicationMapper {
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
    boolean updateApplicationAuditTime(@Param("auditTime") Date auditTime,
                                       @Param("id") int id);

    /**
     * 修改申请审核状态
     * @param approval
     * @param id
     * @return
     */
    boolean updateApplicationApproval(@Param("approval") int approval,
                                      @Param("id") int id);

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
    boolean deleteApplication(@Param("deleteFlag") int deleteFlag,
                              @Param("id") int id);
}
