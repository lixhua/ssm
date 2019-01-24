package com.dianke.employee.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalaryCard {
	 
	private Integer id;
	
	private String wname;
	
	private BigDecimal basic;
	
	private BigDecimal bonus;
	
	private BigDecimal fine;
	
	private BigDecimal wage_payable;
	
	private BigDecimal real_wages;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date ceateTime;

	public SalaryCard(String wname,BigDecimal basic,BigDecimal bonus,BigDecimal fine,BigDecimal wage_payable,BigDecimal real_wages){
		this.wname = wname;
		this.basic = basic;
		this.bonus = bonus;
		this.fine = fine;
		this.wage_payable = wage_payable;
		this.real_wages = real_wages;
	}
}
