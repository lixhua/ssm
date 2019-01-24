package com.dianke.employee.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dianke.employee.dao.ProcessLogDao;
import com.dianke.employee.domain.ProcessLog;
import com.dianke.employee.service.ProcessLogService;

@Service
@Transactional
public class ProcessLogServiceImpl implements ProcessLogService {

	@Resource
	private ProcessLogDao processLogDaoImpl;

	@Override
	public void save(ProcessLog pl) {
		processLogDaoImpl.save(pl);
	}

}
