package com.dianke.employee.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dianke.employee.base.ProcessMethod;
import com.dianke.employee.base.annotation.ArchiveLog;
import com.dianke.employee.base.domain.Datas;
import com.dianke.employee.base.domain.Message;
import com.dianke.employee.domain.Emp;
import com.dianke.employee.service.EmpService;

@RestController
@RequestMapping("/emp")
public class EmpController {

	@Resource
	private EmpService empServiceImpl;
	
	@Value("${exportPath}")
	private String exportPath;
	
	@Value("${temp}")
	private String temp;
	
	@ArchiveLog(value="查询用户",method=ProcessMethod.query)
	@RequestMapping(method=RequestMethod.GET)
	public Object getPageUsers(String ename,String job,@DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,Integer deptno,Integer limit,Integer offset) {
		if(limit == null) {
			return "";
		}
		List<Emp> list=  (List<Emp>) empServiceImpl.getPageDatas(ename, job , beginDate, endDate, deptno, limit, offset);
		long l =  empServiceImpl.getTotal(ename,job, beginDate, endDate, deptno);
		Datas datas = new Datas(l,list);
		return datas;
	}
	
	@ArchiveLog(value="根据ID值查询用户,用于编辑",method=ProcessMethod.query)
	@RequestMapping(value="/{empno}",method=RequestMethod.GET)
	public Emp getById(@PathVariable(name="empno") Integer empno) {
		return empServiceImpl.getById(empno);
	}
	
	@ArchiveLog(value="更新用户，被更新的用户ID位: ",method=ProcessMethod.update,parmeters= {0,1})
	@RequestMapping(value="/{empno}",method=RequestMethod.PUT)
	public Object update(@PathVariable(name="empno") Integer empno, Integer deptno, String ename, 
    		@DateTimeFormat(pattern="yyyy-MM-dd") Date hiredate,Integer mgr, String job, Integer sal, Integer comm) {
		Message msg = null;
		try {
			empServiceImpl.update(empno, deptno, ename, hiredate, mgr, job, sal, comm);
			
			msg = new Message(1,true,"更新成功");
		}catch(Exception ex) {
			
			msg = new Message(-1,false,"更新失败，数据库异常");
		}
		return msg;
	}
	
	@ArchiveLog(value="根据ID删除用户,删除用户ID为: ",method=ProcessMethod.delete,parmeters= {0})
	@RequestMapping(method=RequestMethod.DELETE)
	public Object delete(String empnos) {
		List<Integer> list = new ArrayList<>();
		String[] strs = empnos.split(",");
		for(String str : strs) {
			list.add(Integer.parseInt(str));
		}
		Message msg = null;
		try {
			empServiceImpl.batchDelete(list);
			
			msg = new Message(1,true,"删除成功");
		}catch(Exception ex) {
			
			msg = new Message(-1,false,"删除失败，数据库异常");
		}
		return msg;
	}
	
	@ArchiveLog(value="添加用户",method=ProcessMethod.add)
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object add(String ename,String job,Integer mgr,Integer sal,Integer comm,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date hiredate,Integer deptno) {
		Message msg = null;
		try {
			Emp emp = new Emp();
			emp.setEname(ename);
			emp.setJob(ename);
			emp.setMgr(mgr);
			emp.setSal(sal);
			emp.setComm(comm);
			emp.setHiredate(hiredate);
			emp.setDeptno(deptno);
			
			empServiceImpl.add(emp);
			
			msg = new Message(1,true,"添加成功");
		}catch(Exception ex) {
			
			msg = new Message(-1,false,"添加失败，数据库异常");
		}
		return msg;
	}
	
	@RequestMapping("/export")
	public Object exportExcel(String ename,String job,@DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,Integer deptno,HttpServletResponse resp) {
		Message msg = null;
		try {
			String fileName = empServiceImpl.generateExcel(ename, job, beginDate, endDate, deptno);
			resp.setContentType("application/octet-stream;charset=utf-8");
			resp.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
			FileCopyUtils.copy(new FileInputStream(exportPath + fileName), new FileOutputStream(temp + fileName));
			
			msg = new Message(1,true,"员工管理数据导出成功,请在" + temp + "查找文件: " + fileName);
		} catch (Exception e) {
			msg = new Message(-1,false,"员工管理数据导出失败，请联系管理员。");
			e.printStackTrace();
		}
		return msg;
	}
	
	@RequestMapping("/template")
	public Object template() {
		Message msg = null;
		try {
			String fileName = empServiceImpl.template();
			FileCopyUtils.copy(new FileInputStream(exportPath + fileName), new FileOutputStream(temp + fileName));
			msg = new Message(1,true,"员工管理模板导出成功,请在" + temp + "查找文件: " + fileName);
		} catch (Exception e) {
			msg = new Message(-1,false,"员工管理模板导出失败，请联系管理员。");
			e.printStackTrace();
		}
		return msg;
	}
	
}
