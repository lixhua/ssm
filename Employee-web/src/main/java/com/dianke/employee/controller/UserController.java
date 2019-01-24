package com.dianke.employee.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dianke.employee.base.domain.Datas;
import com.dianke.employee.base.domain.Message;
import com.dianke.employee.domain.User;
import com.dianke.employee.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userServiceImpl;
	
	@RequestMapping(method=RequestMethod.GET)
	public Object getPageUsers(String name,String gender,@DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
			@DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,int limit,int offset) {
		List<User> list=  (List<User>) userServiceImpl.getPageDatas(name, gender, beginDate, endDate, limit, offset);
		long l =  userServiceImpl.getTotal(name, gender, beginDate, endDate);
		Datas datas = new Datas(l,list);
		return datas;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public Object delete(String ids) {
		List<Integer> list = new ArrayList<>();
		String[] strs = ids.split(",");
		for(String str : strs) {
			list.add(Integer.parseInt(str));
		}
		Message msg = null;
		try {
			userServiceImpl.batchDelete(list);
			
			msg = new Message(1,true,"删除成功");
		}catch(Exception ex) {
			
			msg = new Message(-1,false,"删除失败，数据库异常");
		}
		return msg;
	}
}
