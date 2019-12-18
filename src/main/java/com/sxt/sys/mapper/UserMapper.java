package com.sxt.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxt.sys.domain.User;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since
 */
public interface UserMapper extends BaseMapper<User> {

    User persUsersByDeptIdlist(Integer id);

   /* int updateUser(User user);*/

}
