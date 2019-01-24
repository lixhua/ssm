package com.dianke.employee.service.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dianke.employee.dao.EmpDao;
import com.dianke.employee.domain.Emp;
import com.dianke.employee.service.EmpService;
import com.dianke.employee.utils.DateUtils;
import com.dianke.employee.utils.ExportExcel;
import com.dianke.employee.utils.ImportExcel;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {


	@Value("${exportPath}")
	private String exportPath;
	
	@Resource
	private EmpDao empDaoImpl;
	
	@Override
	public List<Emp> getPageDatas(String ename, String job, Date beginDate, Date endDate, Integer deptno, int limit,
			int beginIndex) {
		ename = !"".equals(ename) ? "%" + ename + "%" :ename;
		job = !"".equals(job) ? "%" + job + "%" : job;
		return empDaoImpl.getPageDatas(ename, job, beginDate, endDate, deptno, limit, beginIndex);
	}

	@Override
	public long getTotal(String ename, String job, Date beginDate, Date endDate, Integer deptno) {
		ename = !"".equals(ename) ? "%" + ename + "%" :ename;
		job = !"".equals(job) ? "%" + job + "%" : job;
		return empDaoImpl.getTotal(ename, job, beginDate, endDate, deptno);
	}

	@Override
	public Emp getById(Integer empno) {
		return empDaoImpl.getById(empno);
	}

	@Override
	public void batchDelete(List<Integer> empnos) {
		empDaoImpl.batchDelete(empnos);
	}

	@Override
	public void update(Integer empno, Integer deptno, String ename, Date hiredate, Integer mgr, String job, Integer sal,
			Integer comm) {
		empDaoImpl.update(empno, deptno, ename, hiredate, mgr, job, sal, comm);
	}

	@Override
	public void add(Emp emp) {
		empDaoImpl.add(emp);
	}

	@Override
	public String generateExcel(String ename, String job, Date beginDate, Date endDate, Integer deptno) throws Exception {
		ename = !"".equals(ename) ? "%" + ename + "%" :ename;
		job = !"".equals(job) ? "%" + job + "%" : job;
		List<Emp> list = empDaoImpl.generateExcel(ename, job, beginDate, endDate, deptno);
		
		String title = "员工管理";
		String[] rows = {"工号","员工姓名","工种","上级领导","入职日期","工资","奖金","部门编号"};
		List<Object[]> datas = new ArrayList<>();
		list.forEach(e -> {
			Object[] obj = {
				e.getEmpno(),
				e.getEname(),
				e.getJob(),
				null != e.getMgr() ? e.getMgr() : 0,
				DateUtils.transferDateToStr(e.getHiredate(),DateUtils.DATE_SMALL_STR),
				null != e.getSal() ? e.getSal() : 0,
				null != e.getComm() ? e.getComm() : 0,
				null != e.getDeptno() ? e.getDeptno() : 0
			};
			datas.add(obj);
		});
		
		ExportExcel exportExcel = new ExportExcel(title,rows,datas);
		String fileName = System.nanoTime() +  ".xls";
		exportExcel.export(exportPath + fileName);
		return fileName;
	}

	@Override
	public String template() throws Exception {
		Emp e = new Emp("张三","清洁工",1111,DateUtils.strToDate("1999-12-12", DateUtils.DATE_SMALL_STR),4000,200,10);
		String title = "员工管理";
		String[] rows = {"员工姓名","工种","上级领导","入职日期","工资","奖金","部门编号"};
		List<Object[]> datas = new ArrayList<>();
		Object[] obj = {
				e.getEname(),
				e.getJob(),
				null != e.getMgr() ? e.getMgr() : 0,
				DateUtils.transferDateToStr(e.getHiredate(),DateUtils.DATE_SMALL_STR),
				null != e.getSal() ? e.getSal() : 0,
				null != e.getComm() ? e.getComm() : 0,
				null != e.getDeptno() ? e.getDeptno() : 0
		};
		datas.add(obj);
		ExportExcel exportExcel = new ExportExcel(title,rows,datas);
		String fileName = "员工管理模板.xls";
		exportExcel.export(exportPath + fileName);
		return fileName;
	}

	@Override
	public void importEmp(String importName) throws Exception {
		ImportExcel ie = new ImportExcel();
		List<Emp> emps = new ArrayList<>();
		Map<Integer, String> map = ie.readExcelContent(new FileInputStream(importName));
		System.out.println(importName);
		map.forEach((k,v) -> {
			if(k > 3) {
				Emp emp = new Emp();
				String[] strings = v.split("\\s+");
				emp.setEname(strings[1] != null ? strings[1] : "");
				emp.setJob(strings[2] != null ? strings[2] : "");
				emp.setMgr(Integer.parseInt(strings[3] != null ? strings[3] : "0"));
				emp.setHiredate(DateUtils.strToDate(strings[4], DateUtils.DATE_SMALL_STR));
				emp.setSal(Integer.parseInt(strings[5] != null ? strings[5] : "0"));
				emp.setComm(Integer.parseInt(strings[6] != null ? strings[6] : "0"));
				emp.setDeptno(Integer.parseInt(strings[7] != null ? strings[7] : "0"));
				emps.add(emp);
			}
		});
		empDaoImpl.importEmp(emps);
	}
}
