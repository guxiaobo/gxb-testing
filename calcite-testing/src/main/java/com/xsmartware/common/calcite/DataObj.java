package com.xsmartware.common.calcite;

import java.math.BigDecimal;
import java.util.Date;

public class DataObj {
	
	public Long aid;
	public String name;
	public Date atime;
	public BigDecimal amount;
	public Boolean flag;
	
	public DataObj(Long id, String name, Date time, 
			BigDecimal amount, Boolean flag) {
		this.aid = id;
		this.name = name;
		this.atime = time;
		this.amount = amount;
		this.flag = flag;
	}

}
