package com.dianke.employee.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

	private Integer id;
	
	private String name;
	
	private String gender;
	
	private String email;
	
	@JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
	private Date birthday;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp createtime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp updatetime;
	
}
