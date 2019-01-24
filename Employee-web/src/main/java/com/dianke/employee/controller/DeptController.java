package com.dianke.employee.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dianke.employee.base.domain.Datas;
import com.dianke.employee.base.domain.Message;
import com.dianke.employee.domain.Dept;
import com.dianke.employee.service.DeptService;

@RestController
@RequestMapping("/dept")
public class DeptController {

	@Resource
	private DeptService deptServiceImpl;
	
	@RequestMapping(method=RequestMethod.GET)
	public Object getAll() {
		return deptServiceImpl.getAll();
	}
	
	@RequestMapping("/all")
	public Object getAllDept(HttpServletRequest req) {
		List<Dept> list = deptServiceImpl.getAll();
		
		long l = deptServiceImpl.getLong();
		
		Datas datas = new Datas(l,list);
		Message msg = new Message(1,true,"");
		req.getSession().setAttribute("importMsg", msg);
		return datas;
	}
	
	@RequestMapping(value="/{deptno}",method=RequestMethod.GET)
	public Dept getById(@PathVariable(name="deptno") Integer deptno) {
		return deptServiceImpl.getById(deptno);
	}
	
	@RequestMapping(value="/{deptno}",method=RequestMethod.PUT)
	public Object update(@PathVariable(name="deptno") Integer deptno, String loc, String dname) {
		Message msg = null;
		try {
			deptServiceImpl.upData(deptno,dname,loc);
			
			msg = new Message(1,true,"更新成功");
		}catch(Exception ex) {
			
			msg = new Message(-1,false,"更新失败，数据库异常");
		}
		return msg;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public Object delete(String deptnos) {
		List<Integer> list = new ArrayList<>();
		String[] strs = deptnos.split(",");
		for(String str : strs) {
			list.add(Integer.parseInt(str));
		}
		Message msg = null;
		try {
			deptServiceImpl.bachDelete(list);
			
			msg = new Message(1,true,"删除成功");
		}catch(Exception ex) {
			
			msg = new Message(-1,false,"删除失败，数据库异常");
		}
		return msg;
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Object add(String dname, Integer deptno, String loc) {
		Message msg = null;
		try {
			Dept dept = new Dept();
			dept.setDeptno(deptno);
			dept.setDname(dname);
			dept.setLoc(loc);
			
			deptServiceImpl.add(dept);
			
			msg = new Message(1,true,"添加成功");
		}catch(Exception ex) {
			
			msg = new Message(-1,false,"添加失败，数据库异常");
		}
		return msg;
	}
	
}
