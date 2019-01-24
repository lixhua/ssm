package com.dianke.employee.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

	private Integer deptno;
	
	private String dname;
	
	private String loc;
}
