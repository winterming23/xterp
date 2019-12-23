package com.sxt.sys.service.winter.impl;

import com.sxt.sys.domain.winter.Application;
import com.sxt.sys.mapper.winter.ApplicationMapper;
import com.sxt.sys.service.winter.ApplicationServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/10 10:13
 */
@Service
@SuppressWarnings("all")
public class ApplicationServiceImpl implements ApplicationServiceI {
    @Autowired
    private ApplicationMapper applicationMapper;
    /**
     * 根据申请人
     * 查询考勤申请数据
     *
     * @param proposer
     * @return
     */
   /* @Override
    public List<Application> getAllApplicationProposer(int proposer) {
        return applicationMapper.getAllApplicationProposer(proposer);
    }*/

    /**
     * 根据审核人
     * 查询所有的考勤申请数据
     *
     * @param auditor
     * @return
     */
    /*@Override
    public List<Application> getAllApplicationAuditor(int auditor) {
        return applicationMapper.getAllApplicationAuditor(auditor);
    }*/

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Application> getAllApplication() {
        return applicationMapper.getAllApplication();
    }

    /**
     * 修改考勤审核时间
     *
     * @param auditTime
     * @param id
     * @return
     */
    @Override
    public boolean updateApplicationAuditTime(Date auditTime, int id) {
        return applicationMapper.updateApplicationAuditTime(auditTime,id);
    }

    /**
     * 修改申请审核状态
     *
     * @param approval
     * @param id
     * @return
     */
    @Override
    public boolean updateApplicationApproval(int approval, int id) {
        return applicationMapper.updateApplicationApproval(approval,id);
    }

    /**
     * 添加考勤信息
     *
     * @param application
     * @return
     */
    @Override
    public boolean saveApplication(Application application) {
        return applicationMapper.saveApplication(application);
    }

    /**
     * 删除标记删除考勤信息
     *
     * @param deleteFlag
     * @param id
     * @return
     */
    @Override
    public boolean deleteApplication(int deleteFlag, int id) {
        return applicationMapper.deleteApplication(deleteFlag,id);
    }
}
