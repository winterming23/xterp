package com.sxt.sys.service.winter;


import com.sxt.sys.domain.winter.Leave;

import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/10 10:16
 * 请假 业务操作接口
 */
public interface LeaveServiceI {
    /**
     * 添加请假数据
     * @param leave
     * @return
     */
    boolean saveLeave(Leave leave);

    /**
     * 修改请假数据
     * @param leave
     * @return
     */
    boolean updateLeave(Leave leave);

    /**
     * 根据编号修改状态
     * 用于审核是否通过请假
     * @param leaveId
     * @param verifyStatus
     * @return
     */
    boolean updateLeaveStatus(int leaveId, int verifyStatus);

    /**
     * 根据编号删除请假数据
     * @param deleteFlag
     * @param leaveId
     * @return
     */
    boolean deleteLeave(int deleteFlag, int leaveId);

    /**
     * 未被删除的请假数据
     * @return
     */
    List<Leave> getAllNoDeleteLeave();

    /**
     * 已被删除的请假数据
     * @return
     */
    List<Leave> getAllDeleteLeave();

    /**
     * 所有请假数据
     * @return
     */
    List<Leave> getAllLeave();

    /**
     * 根据编号查询单条请假数据
     * @param leaveId
     * @return
     */
    Leave findLeaveOne(int leaveId);
}
