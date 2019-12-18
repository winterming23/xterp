package com.sxt.sys.service.qxs.finance;

import com.sxt.sys.domain.qxs.finance.Account;

import java.util.List;

/**
 * 账号信息业务处理
 */
public interface AccountServiceI {

    /**
     * 查询所有未删除的账号信息
     * @return
     */
    List<Account> queryNotDeleteAccount();

    /**
     * 查询所有已删除的记录
     * @return
     */
    List<Account> queryDeleteAccount();

    /**
     * 查询所有数据包括已删除的
     * @return
     */
    List<Account> queryAllAccount();

    /**
     * 根据id查询当前信息
     * @param id
     * @return
     */
    Account getOneAccount(Integer id);

    /**
     * 修改账号信息
     * @param account
     * @return
     */
    boolean updateAccount(Account account);

    /**
     * 标记删除
     * @param deleteFlag  0：未删除；1：已删除； 默认 0
     * @param id
     * @return
     */
    boolean deleteAccount(Integer deleteFlag, Integer id);

    /**
     * 新增账号信息
     * @param account
     * @return
     */
    boolean addAccount(Account account, String name);
}
