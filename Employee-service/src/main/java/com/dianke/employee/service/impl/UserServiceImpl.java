package com.dianke.employee.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dianke.employee.dao.UserDao;
import com.dianke.employee.domain.User;
import com.dianke.employee.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDaoImpl;
	
	@Override
	public List<User> getPageDatas(String name, String gender, Date beginDate, Date endDate, int limit,
			int beginIndex) {
		name = !"".equals(name) ? "%" + name + "%" :name;
		return userDaoImpl.getPageDatas(name, gender, beginDate, endDate, limit, beginIndex);
	}

	@Override
	public long getTotal(String name, String gender, Date beginDate, Date endDate) {
		return userDaoImpl.getTotal(name, gender, beginDate, endDate);
	}

	@Override
	public void batchDelete(List<Integer> ids) {
		userDaoImpl.batchDelete(ids);
	}

}
