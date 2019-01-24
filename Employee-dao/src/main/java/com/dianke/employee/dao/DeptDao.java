package com.dianke.employee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dianke.employee.domain.Dept;

public interface DeptDao {

	public List<Dept> getAll();
	
	public long getLong();
	
	public Dept getById(Integer deptno);
	
	public void bachDelete(List<Integer> deptnos);
	
	public void upData(@Param("deptno") Integer deptno, @Param("dname") String dname, @Param("loc") String loc);
	
	public void add(Dept dept);
}
