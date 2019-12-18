package com.sxt.sys.mapper.winter;

import com.sxt.sys.domain.winter.Recruit;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Winter
 * @Date 2019/12/2 16:51
 * 招聘 持久操作实现接口
 */

public interface RecruitMapper {
    /**
     * 添加招聘信息
     * @param recruit
     * @return
     */
    boolean saveRecruit(Recruit recruit);

    /**
     * 修改招聘信息
     * @param recruit
     * @return
     */
    boolean updateRecruit(Recruit recruit);

    /**
     * 删除招聘信息
     * @param deleteFlag
     * @param recruitId
     * @return
     */
    boolean deleteRecruit(@Param("deleteFlag") int deleteFlag,
                          @Param("recruitId") int recruitId);

    /**
     * 查询所有未删除的招聘信息
     * @return
     */
    List<Recruit> getAllNoDeleteRecruit();

    /**
     *查询所有已删除的招聘信息
     * @return
     */
    List<Recruit> getAllDeleteRecruit();

    /**
     * 查询所有招聘信息
     * @return
     */
    List<Recruit> getAllRecruit();

    /**
     * 根据编号查询单条招聘信息
     * @param recruitId
     * @return
     */
    Recruit findRecruitOne(int recruitId);
}
