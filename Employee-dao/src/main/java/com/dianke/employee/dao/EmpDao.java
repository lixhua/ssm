package com.dianke.employee.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dianke.employee.domain.Emp;

public interface EmpDao {

	public List<Emp> getPageDatas(@Param("ename") String ename, @Param("job") String job, 
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("deptno") Integer deptno,
			 @Param("limit") int limit, @Param("beginIndex") int beginIndex);
	
	public long getTotal(@Param("ename") String ename, @Param("job") String job, 
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("deptno") Integer deptno);

	public Emp getById(Integer empno);
	
	public void batchDelete(List<Integer> empnos);
	
	public void update(@Param("empno") Integer empno, @Param("deptno") Integer deptno,
			 @Param("ename") String ename,  @Param("hiredate") Date hiredate, @Param("mgr") Integer mgr,
			 @Param("job")String job, @Param("sal") Integer sal, @Param("comm") Integer comm);
	
	public void add(Emp emp);
	
	public List<Emp> generateExcel(@Param("ename") String ename, @Param("job") String job, 
			@Param("beginDate") Date beginDate, @Param("endDate") Date endDate, @Param("deptno") Integer deptno);
	
	public void importEmp(List<Emp> emps);
}
