package com.dianke.employee.service;

import java.util.List;

import com.dianke.employee.domain.Dept;

public interface DeptService {

	public List<Dept> getAll();
	
	public long getLong();
	
	public Dept getById(Integer deptno);
	
	public void bachDelete(List<Integer> deptnos);
	
	public void upData(Integer deptno, String dname, String loc);
	
	public void add(Dept dept);
}
