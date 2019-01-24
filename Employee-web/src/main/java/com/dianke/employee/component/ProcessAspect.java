package com.dianke.employee.component;

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.dianke.employee.base.ProcessMethod;
import com.dianke.employee.base.annotation.ArchiveLog;
import com.dianke.employee.domain.ProcessLog;
import com.dianke.employee.domain.SysUser;
import com.dianke.employee.service.ProcessLogService;

@Component
@Aspect
public class ProcessAspect {

	@Resource
	private ProcessLogService processLogServiceImpl;
	
	@Before("within(com.rsy.managerbatis.controller..*) && @annotation(archiveLog)")
	public void before(JoinPoint jp,ArchiveLog archiveLog) {
		ProcessLog pl = new ProcessLog();
		String processInfo = archiveLog.value();
		ProcessMethod method = archiveLog.method();
		
		if(ProcessMethod.update.equals(method) || ProcessMethod.delete.equals(method)) {
			int[] params = archiveLog.parmeters();
			
			Object[] objs = jp.getArgs();
			for(int i : params) {
				processInfo += objs[i] + "	";
			}
		}
		
		pl.setCreateTime(new Timestamp(new Date().getTime()));
		pl.setProMsg(processInfo);
		
		HttpServletRequest req = getRequest();
		
		HttpSession session = req.getSession();
		SysUser sysUser = (SysUser) session.getAttribute("userInfo");
		if(null != sysUser) {
			pl.setUserId(sysUser.getId());
			pl.setUsername(sysUser.getNicky_name());
		}
		
		processLogServiceImpl.save(pl);
	}

	private HttpServletRequest getRequest() {
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		ServletRequestAttributes rsa = (ServletRequestAttributes) ra;
		return rsa.getRequest();
	}
}
