package com.sxt.sys.service.qxs.finance.impl;

import com.sxt.sys.domain.qxs.finance.Account;
import com.sxt.sys.mapper.qxs.finance.AccountMapper;
import com.sxt.sys.service.qxs.finance.AccountServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountServiceI {

    @Autowired
    private AccountMapper accountMapper;

    /**
     * 查询所有未删除的账号
     * @return
     */
    @Override
    public List<Account> queryNotDeleteAccount() {
        return accountMapper.queryNotDeleteAccount();
    }

    /**
     * 查询被删除的账号信息
     * @return
     */
    @Override
    public List<Account> queryDeleteAccount() {
        return accountMapper.queryDeleteAccount();
    }

    /**
     * 查询所有
     * @return
     */
    @Override
    public List<Account> queryAllAccount() {
        return accountMapper.queryAllAccount();
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Override
    public Account getOneAccount(Integer id) {
        return accountMapper.getOneAccount(id);
    }

    /**
     * 修改
     * @param account
     * @return
     */
    @Override
    public boolean updateAccount(Account account) {
        return accountMapper.updateAccount(account);
    }

    /**
     * 标记删除
     * @param deleteFlag  0：未删除；1：已删除； 默认 0
     * @param id
     * @return
     */
    @Override
    public boolean deleteAccount(Integer deleteFlag, Integer id) {
        return accountMapper.deleteAccount(deleteFlag,id);
    }

    /**
     * 新增 先获取供应商/客户的信息
     * @param account
     * @return
     */
    @Override
    public boolean addAccount(Account account,String name) {
        //根据name 查询供应商/客户 的id，将数据set到Account中

        boolean b = accountMapper.addAccount(account);
        return b;
    }
}
