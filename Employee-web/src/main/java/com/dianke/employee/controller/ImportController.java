package com.dianke.employee.controller;

import java.io.FileInputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.dianke.employee.service.AttendanceService;
import com.dianke.employee.service.SalaryCarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dianke.employee.base.domain.Message;
import com.dianke.employee.service.EmpService;
import com.dianke.employee.utils.ImportExcel;

@Controller
@RequestMapping("/import")
public class ImportController {

	@Resource
	private EmpService empServiceImpl;

	@Resource
	private AttendanceService attendanceServiceImpl;

	@Resource
	private SalaryCarService salaryCarServiceImpl;
	
	@Value("${temp}")
	private String temp;
	
	@RequestMapping(method=RequestMethod.POST)
	public String toSave(MultipartHttpServletRequest req,HttpServletResponse resp) {
		Message msg = null;
		try {
			MultipartFile mf = req.getFile("profile");
			String oldFileName = mf.getOriginalFilename(); //获取文件名
			String newFilename = temp + oldFileName;
			ImportExcel ie = new ImportExcel();
			String[] strs = ie.readExcelTitle(new FileInputStream(newFilename));
			for(String str : strs) {
				if(str.equals("员工管理")) {
					empServiceImpl.importEmp(newFilename);
					msg = new Message(1,true,"导入成功。");
				}
				if(str.equals("出勤信息")) {
					attendanceServiceImpl.importAttendance(newFilename);
					msg = new Message(1,true,"导入成功。");
				}
				if(str.equals("工资卡")) {
					salaryCarServiceImpl.importSalaryCard(newFilename);
					msg = new Message(1,true,"导入成功。");
				}
			}
		} catch (Exception e) {
			msg = new Message(-1,false,"导入失败，请检查文件是否导入正确。");
			e.printStackTrace();
		}
		req.getSession().setAttribute("importMsg", msg);
		return "redirect:homepage";
	}
}
