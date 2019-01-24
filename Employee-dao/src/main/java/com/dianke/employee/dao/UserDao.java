package com.dianke.employee.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dianke.employee.domain.User;

@Repository
@Transactional
public interface UserDao {

	public List<User> getPageDatas(@Param("name") String name, @Param("gender") String gender, 
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("limit") int limit, @Param("beginIndex") int beginIndex);
	
	public long getTotal(@Param("name") String name, @Param("gender") String gender, 
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

	public void batchDelete(List<Integer> ids);
	
}
