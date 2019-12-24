package com.sxt.sys.service.winter.impl;

import com.sxt.sys.domain.winter.Leave;
import com.sxt.sys.mapper.winter.LeaveMapper;
import com.sxt.sys.service.winter.LeaveServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/10 10:18
 * 请假业务处理实现类
 */
@SuppressWarnings("all")
@Service
public class LeaveServiceImpl implements LeaveServiceI {
    @Autowired
    private LeaveMapper leaveMapper;
    /**
     * 添加请假数据
     *
     * @param leave
     * @return
     */
    @Override
    public boolean saveLeave(Leave leave) {
        return leaveMapper.saveLeave(leave);
    }

    /**
     * 修改请假数据
     *
     * @param leave
     * @return
     */
    @Override
    public boolean updateLeave(Leave leave) {
        return leaveMapper.updateLeave(leave);
    }

    /**
     * 根据编号修改状态
     * 用于审核是否通过请假
     *
     * @param leaveId
     * @param verifyStatus
     * @return
     */
    @Override
    public boolean updateLeaveStatus(int leaveId, int verifyStatus) {
        return leaveMapper.updateLeaveStatus(leaveId,verifyStatus);
    }

    /**
     * 根据编号删除请假数据
     *
     * @param deleteFlag
     * @param leaveId
     * @return
     */
    @Override
    public boolean deleteLeave(int deleteFlag, int leaveId) {
        return leaveMapper.deleteLeave(deleteFlag,leaveId);
    }

    /**
     * 未被删除的请假数据
     *
     * @return
     */
    @Override
    public List<HashMap> getAllNoDeleteLeave() {
        return leaveMapper.getAllNoDeleteLeave();
    }

    /**
     * 已被删除的请假数据
     *
     * @return
     */
    @Override
    public List<Leave> getAllDeleteLeave() {
        return leaveMapper.getAllDeleteLeave();
    }

    /**
     * 所有请假数据
     *
     * @return
     */
    @Override
    public List<Leave> getAllLeave() {
        return leaveMapper.getAllLeave();
    }

    /**
     * 根据编号查询单条请假数据
     *
     * @param leaveId
     * @return
     */
    @Override
    public Leave findLeaveOne(int leaveId) {
        return leaveMapper.findLeaveOne(leaveId);
    }
}
