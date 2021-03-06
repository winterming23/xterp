package com.sxt.sys.controller;


import com.sxt.sys.common.ActiverUser;
import com.sxt.sys.common.ResultObj;
import com.sxt.sys.common.WebUtils;
import com.sxt.sys.domain.Loginfo;
import com.sxt.sys.domain.winter.WorkAttendance;
import com.sxt.sys.service.LoginfoService;
import com.sxt.sys.service.winter.WorkAttendanceServiceI;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * <p>
 *  登录
 * </p>
 *
 * @author
 * @since
 */
@RestController
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private LoginfoService loginfoService;

	@Autowired
	private WorkAttendanceServiceI workAttendanceService;
	
	@RequestMapping("login")
	public ResultObj login(String loginname,String pwd ,HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		AuthenticationToken token=new UsernamePasswordToken(loginname, pwd);
		try {
			subject.login(token);
			ActiverUser activerUser=(ActiverUser) subject.getPrincipal();
			WebUtils.getSession().setAttribute("user", activerUser.getUser());
			request.getSession().setAttribute("userId",activerUser.getUser().getId());
			//记录登陆日志
			Loginfo entity=new Loginfo();
			entity.setLoginname(activerUser.getUser().getName()+"-"+activerUser.getUser().getLoginname());
			entity.setLoginip(WebUtils.getRequest().getRemoteAddr());
			entity.setLoginip(getIpAddr(request));
			entity.setLogintime(new Date());
			loginfoService.save(entity);
			System.out.println("mima-------------------------->"+pwd);
			//判断是否今天已添加 查询员工 该日期是否已存在 0不存在 1已存在
			int workNull = workAttendanceService.findWorkNull(activerUser.getUser().getId());
			//获取星期几
			String week = request.getParameter("week");
			WorkAttendance workAttendance = new WorkAttendance(null,activerUser.getUser().getId(),new Date(),week,null,null,null,null,0,0);
			if(workNull != 1){
				//添加一条考勤数据
				workAttendanceService.saveWorkAttendance(workAttendance);
			}
			//添加之后存入request对象
			request.getSession().setAttribute("workId",workAttendance.getId());
			return ResultObj.LOGIN_SUCCESS;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return ResultObj.LOGIN_ERROR_PASS;
		}
	}


	/**
	 * 获取ip地址
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ("0:0:0:0:0:0:0:1".equals(ip)) {
			ip = "127.0.0.1";
		}
		if (ip.split(",").length > 1) {
			ip = ip.split(",")[0];
		}
		return ip;
	}
}

