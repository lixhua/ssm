package com.dianke.employee.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dianke.employee.utils.Md5Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dianke.employee.base.domain.Message;
import com.dianke.employee.domain.SysUser;
import com.dianke.employee.emun.AutoLogin;
import com.dianke.employee.service.SysUserService;

@RestController
@RequestMapping("/sys_user")
public class SysUserController {

	@Resource
	private SysUserService sysUserServiceImpl;
	
	@RequestMapping(method=RequestMethod.GET)
	public Object getNameAndPassword(String username,String password,String autoLogin,HttpServletRequest req,HttpServletResponse resp) {
		SysUser sysUser = sysUserServiceImpl.getName(username);
		boolean flag = Md5Utils.validatePassword(password,sysUser.getPassword());

		Message msg = null;
		if(!flag) {
			msg = new Message(-1,false,"用户或者密码错误。");
		}else {
			HttpSession hs = req.getSession();
			hs.setAttribute("userInfo", sysUser);
			AutoLogin al = AutoLogin.valueOf(autoLogin);
			int time = 0;
			Cookie nameCookie = new Cookie("username",username);
			Cookie pwdCookie = new Cookie("password",password);
			switch(al) {
				case never:
					time = 0;
					break;
				case three:
					time = 259200;
					break;
				case seven:
					time = 604800;
					break;
				case month:
					time = 2592000;
					break;
			}
			nameCookie.setMaxAge(time);
			pwdCookie.setMaxAge(time);
			
			resp.addCookie(nameCookie);
			resp.addCookie(pwdCookie);
			
			msg = new Message(1,true,"success");
		}
		return msg;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public Object registered(String username,String password) {
		SysUser sysUser = sysUserServiceImpl.getName(username);
		Message msg = null;
		if(sysUser != null) {
			msg = new Message(-1,false,"用户已存在。");
		}else {
			String md5Password = Md5Utils.encode(password);
			sysUserServiceImpl.registered(username, md5Password);
			
			msg = new Message(1,true,"注册成功。");
		}
		return msg;
	}
}
