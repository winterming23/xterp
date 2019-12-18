package com.sxt.sys.mapper.zqw;

import com.sxt.sys.domain.zqw.Dispatchedworker;

import java.util.HashMap;

/**
 * 派工持久层操作接口
 */
public interface DispatchedworkerMapper {
    /**
     * 查询所有派工信息
     * @return
     */
    HashMap seleDw();

    /**
     * 添加派工信息
     * @param dispatchedworker
     * @return
     */
    boolean inserDw(Dispatchedworker dispatchedworker);

    /**
     * 修改派工信息
     * @param dispatchedworker
     * @return
     */
    boolean updateDw(Dispatchedworker dispatchedworker);

    /**
     * 根据id删除派工信息
     * @param id
     * @return
     */
    boolean deleltDw(int id);
}
