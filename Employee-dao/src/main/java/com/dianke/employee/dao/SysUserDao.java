package com.dianke.employee.dao;

import org.apache.ibatis.annotations.Param;

import com.dianke.employee.domain.SysUser;

public interface SysUserDao {

	public SysUser getNameAndPassword(@Param("username") String username, @Param("password") String password);
	
	public SysUser getName(String username);
	
	public void registered(@Param("username") String username, @Param("password") String password);
}
