package com.dianke.employee.service;

import java.util.Date;
import java.util.List;

import com.dianke.employee.domain.Emp;

public interface EmpService {

	public List<Emp> getPageDatas(String ename,String job,Date beginDate, Date endDate, Integer deptno,int limit,int beginIndex);
	
	public long getTotal(String name, String job, Date beginDate, Date endDate, Integer deptno);

	public Emp getById(Integer empno);
	
	public void batchDelete(List<Integer> empnos);
	
	public void update(Integer empno, Integer deptno, String ename, Date hiredate, Integer mgr, String job, Integer sal, Integer comm);
	
	public void add(Emp emp);
	
	public String generateExcel(String name, String job, Date beginDate, Date endDate, Integer deptno)throws Exception;
	
	public String template() throws Exception;
	
	public void importEmp(String importName) throws Exception ;
}
