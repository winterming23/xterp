package com.sxt.sys.service.winter.impl;

import com.sxt.sys.domain.winter.ApplyFor;
import com.sxt.sys.mapper.winter.ApplyForMapper;
import com.sxt.sys.service.winter.ApplyForServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/6 15:07
 */
@Service
@SuppressWarnings("all")
public class ApplyForServiceImpl implements ApplyForServiceI {
    @Autowired
    private ApplyForMapper applyForMapper;

    /**
     * 查看所有未删除的申请数据
     *
     * @return
     */
    @Override
    public List<ApplyFor> getAllNoDeleteApplyFor() {
        return applyForMapper.getAllNoDeleteApplyFor();
    }

    /**
     * 查看所有已删除的申请数据
     *
     * @return
     */
    @Override
    public List<ApplyFor> getAllDeleteApplyFor() {
        return applyForMapper.getAllDeleteApplyFor();
    }

    /**
     * 查看所有申请数据
     * 倒序
     *
     * @return
     */
    @Override
    public List<ApplyFor> getAllApplyFor() {
        return applyForMapper.getAllApplyFor();
    }

    /**
     * 根据编号查询单条申请数据
     *
     * @param id
     * @return
     */
    @Override
    public ApplyFor findApplyFoyOne(int id) {
        return applyForMapper.findApplyFoyOne(id);
    }

    /**
     * 添加申请数据
     *
     * @param applyFor
     * @return
     */
    @Override
    public boolean saveApplyFor(ApplyFor applyFor) {
        return applyForMapper.saveApplyFor(applyFor);
    }

    /**
     * 修改申请数据
     *
     * @param applyFor
     * @return
     */
    @Override
    public boolean updateApplyFor(ApplyFor applyFor) {
        return applyForMapper.updateApplyFor(applyFor);
    }

    /**
     * 删除申请数据
     *
     * @param deleteFlag
     * @param id
     * @return
     */
    @Override
    public boolean deleteApplyFor(int deleteFlag, int id) {
        return applyForMapper.deleteApplyFor(deleteFlag,id);
    }
}
