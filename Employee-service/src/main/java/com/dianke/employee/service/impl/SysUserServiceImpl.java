package com.dianke.employee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dianke.employee.dao.SysUserDao;
import com.dianke.employee.domain.SysUser;
import com.dianke.employee.service.SysUserService;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

	@Resource
	private SysUserDao sysUserDaoImpl;
	
	
	@Override
	public SysUser getNameAndPassword(String username, String password) {
		return sysUserDaoImpl.getNameAndPassword(username, password);
	}


	@Override
	public SysUser getName(String username) {
		return sysUserDaoImpl.getName(username);
	}


	@Override
	public void registered(String username, String password) {
		sysUserDaoImpl.registered(username, password);
	}

}
