package com.sxt.sys.controller;


import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sxt.sys.common.*;
import com.sxt.sys.domain.Dept;
import com.sxt.sys.domain.Role;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.DeptService;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author
 * @since
 */
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 用户全查询
	 */
	@RequestMapping("loadAllUser")
	public DataGridView loadAllUser(UserVo userVo) {
		IPage<User> page=new Page<>(userVo.getPage(), userVo.getLimit());
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq(StringUtils.isNotBlank(userVo.getName()), "loginname", userVo.getName()).or().eq(StringUtils.isNotBlank(userVo.getName()), "name", userVo.getName());
		queryWrapper.eq(StringUtils.isNotBlank(userVo.getAddress()), "address", userVo.getAddress());
		queryWrapper.eq("type", Constast.USER_TYPE_NORMAL);//查询系统用户
		queryWrapper.eq(userVo.getDeptid()!=null, "deptid",userVo.getDeptid());
		this.userService.page(page, queryWrapper);
		
		
		System.out.println(userService.getClass().getSimpleName());
		List<User> list = page.getRecords();
		for (User user : list) {
			Integer deptid = user.getDeptid();
			if(deptid!=null) {
				Dept one =deptService.getById(deptid); //查询部门
				user.setDeptname(one.getTitle());
			}
			Integer mgr = user.getMgr();
			if(mgr!=null) {
				User one = this.userService.getById(mgr);
				user.setLeadername(one.getName());
			}
		}
		
		return new DataGridView(page.getTotal(), list);
		
	}
	
	

	/**
	 * 加载最大的排序码
	 * @param
	 * @return
	 */
	@RequestMapping("loadUserMaxOrderNum")
	public Map<String,Object> loadUserMaxOrderNum(){
		Map<String, Object> map=new HashMap<String, Object>();
		
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.orderByDesc("ordernum");
		IPage<User> page=new Page<>(1, 1);
		List<User> list = this.userService.page(page, queryWrapper).getRecords();
		if(list.size()>0) {
			map.put("value", list.get(0).getOrdernum()+1);
		}else {
			map.put("value", 1);
		}
		return map;
	}
	
	
	/**
	 * 根据部门ID查询用户
	 */
	@RequestMapping("loadUsersByDeptId")
	public DataGridView loadUsersByDeptId(Integer deptid) {
		QueryWrapper<User> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq(deptid!=null, "deptid", deptid);
		queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
		queryWrapper.eq("type", Constast.USER_TYPE_NORMAL);
		List<User> list = this.userService.list(queryWrapper);
		return new DataGridView(list);
	}



	/**
	 * 根据部门ID查询用户(主界面个人资料接口)
	 */
	@RequestMapping("persUsersByDeptId")
	public JSONObject persUsersByDeptId(HttpServletRequest request) {
		Integer deptid= (Integer) request.getSession().getAttribute("userId");
		User user = this.userService.persUsersByDeptIdlist(deptid);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("ad",user);
		return jsonObject;
	}

    /**
     * 修改登录用户信息
     * @param id
     * @return
     */
    @RequestMapping("updateUserss")
	public String updateUser(User user,HttpServletRequest request){
		//商品图片不是默认图片
		if (!(user.getImgpath()!=null&&user.getImgpath().equals(Constast.DEFAULT_IMG))){
			if (user.getImgpath().endsWith("_temp")){
				String newName = AppFileUtils.renameFile(user.getImgpath());
				user.setImgpath(newName);
				//删除原先的图片
				String oldPath = userService.getById(user.getId()).getImgpath();
				AppFileUtils.removeFileByPath(oldPath);
			}
		}

        String salt=IdUtil.simpleUUID().toUpperCase();
        user.setSalt(salt);//设置盐
        user.setPwd(new Md5Hash(user.getPwd(), salt, 2).toString());//设置密码
        boolean b = this.userService.updateById(user);
		HttpSession session=request.getSession();
      /*  int i = userService.updateUser(user);*/
        if (b==true){
            System.out.println(user.getPwd());
            System.out.println("==================更新成功");
			session.setAttribute("user",user);
        }
        /* this.userService.updateById(user);*/

        return "";
	}

	
	/**
	 * 把用户名转成拼音
	 */
	@RequestMapping("changeChineseToPinyin")
	public Map<String,Object> changeChineseToPinyin(String username){
		 Map<String,Object> map=new HashMap<>();
		 if(null!=username) {
			 map.put("value", PinyinUtils.getPingYin(username));
		 }else {
			 map.put("value", "");
		 }
		 return map;
	}

	/**
	 * 添加用户
	 * @param userVo
	 * @return
	 */
	@RequestMapping("addUser")
	public ResultObj addUser(UserVo userVo) {
		try {
			if (userVo.getImgpath()!=null&&userVo.getImgpath().endsWith("_temp")){
				String newName = AppFileUtils.renameFile(userVo.getImgpath());
				userVo.setImgpath(newName);
			}
			userVo.setType(Constast.USER_TYPE_NORMAL);//设置类型
			userVo.setHiredate(new Date());
			String salt=IdUtil.simpleUUID().toUpperCase();
			userVo.setSalt(salt);//设置盐
			userVo.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());//设置密码
			this.userService.save(userVo);
			return ResultObj.ADD_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}

	
	/**
	 * 根据用户ID查询一个用户
	 */
	@RequestMapping("loadUserById")
	public DataGridView loadUserById(Integer id) {
		return new DataGridView(this.userService.getById(id));
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("updateUser")
	public ResultObj updateUser(UserVo userVo) {
		try {
			//商品图片不是默认图片
			if (!(userVo.getImgpath()!=null&&userVo.getImgpath().equals(Constast.DEFAULT_IMG))){
				if (userVo.getImgpath().endsWith("_temp")){
					String newName = AppFileUtils.renameFile(userVo.getImgpath());
					userVo.setImgpath(newName);
					//删除原先的图片
					String oldPath = userService.getById(userVo.getId()).getImgpath();
					AppFileUtils.removeFileByPath(oldPath);
				}
			}
			this.userService.updateById(userVo);
			return ResultObj.UPDATE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}

	/**
	 * 删除用户
	 */
	@RequestMapping("deleteUser")
	public ResultObj deleteUser(Integer id) {
		try {
			this.userService.removeById(id);
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

	/**
	 * 重置用户密码
	 * @param id
	 * @return
	 */
	@RequestMapping("resetPwd")
	public ResultObj resetPwd(Integer id) {
		try {
			User user=new User();
			user.setId(id);
			String salt=IdUtil.simpleUUID().toUpperCase();
			user.setSalt(salt);//设置盐
			user.setPwd(new Md5Hash(Constast.USER_DEFAULT_PWD, salt, 2).toString());//设置密码
			this.userService.updateById(user);
			return ResultObj.RESET_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.RESET_ERROR;
		}
	}
	
	
	/**
	 * 根据用户ID查询角色并选中已拥有的角色
	 */
	@RequestMapping("initRoleByUserId")
	public DataGridView initRoleByUserId(Integer id) {
		//1,查询所有可用的角色
		QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
		queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
		List<Map<String, Object>> listMaps = this.roleService.listMaps(queryWrapper);
		
		//2,查询当前用户拥有的角色ID集合
		List<Integer> currentUserRoleIds=this.roleService.queryUserRoleIdsByUid(id);
		for (Map<String, Object> map : listMaps) {
			Boolean LAY_CHECKED=false;
			Integer roleId=(Integer) map.get("id");
			for (Integer rid : currentUserRoleIds) {
				if(rid==roleId) {
					LAY_CHECKED=true;
					break;
				}
			}
			map.put("LAY_CHECKED", LAY_CHECKED);
		}
		return new DataGridView(Long.valueOf(listMaps.size()), listMaps);
	}
	
	/**
	 * 保存用户和角色的关系
	 */
	@RequestMapping("saveUserRole")
	public ResultObj saveUserRole(Integer uid,Integer[] ids) {
		try {
			this.userService.saveUserRole(uid,ids);
			return ResultObj.DISPATCH_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DISPATCH_ERROR;
		}
		
	}
}

