package com.sxt.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sxt.sys.domain.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 老雷
 * @since 2019-09-20
 */
public interface UserService extends IService<User> {

	/**
	 * 保存用户和角色之间的关系
	 * @param uid
	 * @param ids
	 */
	void saveUserRole(Integer uid, Integer[] ids);

	User persUsersByDeptIdlist(Integer uid);

	/*int updateUser(User user);*/

}
