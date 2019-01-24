package com.dianke.employee.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dianke.employee.domain.ProcessLog;

@Repository
@Transactional
public interface ProcessLogDao {

	public void save(ProcessLog pl);
}
