package com.sxt.sys.service.winter.impl;

import com.sxt.sys.domain.winter.Recruit;
import com.sxt.sys.mapper.winter.RecruitMapper;
import com.sxt.sys.service.winter.RecruitServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/3 8:43
 * 招聘业务实现类
 */
@SuppressWarnings("all")
@Service
public class RecruitServiceImpl implements RecruitServiceI {
    @Autowired
    private RecruitMapper recruitMapper;
    /**
     * 添加招聘信息
     * @param recruit
     * @return
     */
    @Override
    public boolean saveRecruit(Recruit recruit) {
        return recruitMapper.saveRecruit(recruit);
    }

    /**
     * 修改招聘信息
     * @param recruit
     * @return
     */
    @Override
    public boolean updateRecruit(Recruit recruit) {
        return recruitMapper.updateRecruit(recruit);
    }

    /**
     * 根据编号删除招聘信息
     * @param deleteFlag
     * @param recruitId
     * @return
     */
    @Override
    public boolean deleteRecruit(int deleteFlag,int recruitId) {
        return recruitMapper.deleteRecruit(deleteFlag,recruitId);
    }

    /**
     * 查询所有未删除的招聘信息
     *
     * @return
     */
    @Override
    public List<Recruit> getAllNoDeleteRecruit() {
        return recruitMapper.getAllNoDeleteRecruit();
    }

    /**
     * 查询所有已删除的招聘信息
     *
     * @return
     */
    @Override
    public List<Recruit> getAllDeleteRecruit() {
        return recruitMapper.getAllDeleteRecruit();
    }

    /**
     * 查询所有招聘信息
     *
     * @return
     */
    @Override
    public List<Recruit> getAllRecruit() {
        return recruitMapper.getAllRecruit();
    }

    /**
     * 根据编号查询单条招聘信息
     *
     * @param recruitId
     * @return
     */
    @Override
    public Recruit findRecruitOne(int recruitId) {
        return recruitMapper.findRecruitOne(recruitId);
    }
}
