package com.dianke.employee.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dianke.employee.dao.DeptDao;
import com.dianke.employee.domain.Dept;
import com.dianke.employee.service.DeptService;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

	@Resource
	private DeptDao deptDaoImpl;
	
	@Override
	public List<Dept> getAll() {
		return deptDaoImpl.getAll();
	}
	@Override
	public long getLong() {
		return deptDaoImpl.getLong();
	}
	@Override
	public Dept getById(Integer deptno) {
		return deptDaoImpl.getById(deptno);
	}
	@Override
	public void bachDelete(List<Integer> deptnos) {
		deptDaoImpl.bachDelete(deptnos);
	}
	@Override
	public void upData(Integer deptno, String dname, String loc) {
		deptDaoImpl.upData(deptno, dname, loc);
	}
	@Override
	public void add(Dept dept) {
		deptDaoImpl.add(dept);
	}

}
