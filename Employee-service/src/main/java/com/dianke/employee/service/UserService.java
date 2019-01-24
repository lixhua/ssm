package com.dianke.employee.service;

import java.util.Date;
import java.util.List;

import com.dianke.employee.domain.User;

public interface UserService {

	public List<User> getPageDatas(String name, String gender, Date beginDate, Date endDate, int limit, int beginIndex);
	
	public long getTotal(String name, String gender, Date beginDate, Date endDate);

	public void batchDelete(List<Integer> ids);
}
