package com.dianke.employee.service;

import com.dianke.employee.domain.SysUser;

public interface SysUserService {

	public SysUser getNameAndPassword(String username,String password);
	
	public SysUser getName(String username);
	
	public void registered(String username,String password);
	
}
