package com.xsmartware.testing.calcite;

import java.math.BigDecimal;
import java.util.Date;

public class DataObj {
	
	public Long id;
	public String name;
	public Date time;
	public BigDecimal amount;
	public Boolean flag;
	
	public DataObj(Long id, String name, Date time, 
			BigDecimal amount, Boolean flag) {
		this.id = id;
		this.name = name;
		this.time = time;
		this.amount = amount;
		this.flag = flag;
	}

}
