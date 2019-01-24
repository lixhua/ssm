package com.dianke.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SysUser {
	
	private Integer id;
	
	private String nicky_name;
	
	private String password;
}
