package com.dianke.employee.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

	private Integer empno;
	
	private String ename;
	
	private String job;
	
	private Integer mgr;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date hiredate;

	private Integer sal;
	
	private Integer comm;
	
	private Integer deptno;
	
	private Dept dept;

	public Emp(String ename,String job,Integer mgr,Date hiredate,Integer sal,Integer comm,Integer deptno){
		this.ename = ename;
		this.job = job;
		this.mgr = mgr;
		this.hiredate = hiredate;
		this.sal = sal;
		this.comm = comm;
		this.deptno = deptno;
	}
}
