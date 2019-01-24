package com.dianke.employee.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

	private Integer id;
	
	private String aname;
	
	private Integer normal;
	
	private Integer aleave;
	
	private Integer late;
	
	private Integer leave_early;
	
	private Integer absence;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createTime;

	public Attendance(String aname, Integer normal, Integer aleave, Integer late, Integer leave_early, Integer absence){
		this.aname = aname;
		this.normal = normal;
		this.aleave = aleave;
		this.late = late;
		this.leave_early = leave_early;
		this.absence = absence;
	}
}


