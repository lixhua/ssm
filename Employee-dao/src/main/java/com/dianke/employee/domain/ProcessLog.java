package com.dianke.employee.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessLog {

	private Integer id;
	
	private String username;
	
	private Integer userId;
	
	private String proMsg;
	
	private Timestamp createTime;
}
