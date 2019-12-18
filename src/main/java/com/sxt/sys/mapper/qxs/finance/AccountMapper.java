package com.sxt.sys.mapper.qxs.finance;

import com.sxt.sys.domain.qxs.finance.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 账号信息数据操作
 */
public interface AccountMapper {

    /**
     * 查询所有未删除的账号信息
     * @return
     */
    @Select("select * from account where deleteFlag!=1 order by dateCreated desc")
    List<Account> queryNotDeleteAccount();

    /**
     * 查询所有已删除的记录
     * @return
     */
    @Select("select * from account where deleteFlag=1 order by dateCreated desc")
    List<Account> queryDeleteAccount();

    /**
     * 查询所有数据包括已删除的
     * @return
     */
    @Select("select * from account order by dateCreated desc")
    List<Account> queryAllAccount();

    /**
     * 根据id查询当前信息
     * @param id
     * @return
     */
    @Select("select * from account where id=#{id} and deleteFlag!=1 ")
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
    @Update("update account set deleteFlag=#{deleteFlag} where id = #{id}")
    boolean deleteAccount(Integer deleteFlag, Integer id);

    /**
     * 新增账号信息
     * @param account
     * @return
     */
    @Insert("insert into account values(null,#{supplier},#{cardNumber},#{dateCreated},#{remark},0)")
    boolean addAccount(Account account);
}
