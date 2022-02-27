package com.xsmartware.common.calcite;

import java.sql.SQLException;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.tools.RelConversionException;
import org.apache.calcite.tools.ValidationException;

public class CalCiteDataQueryThread extends Thread{
	DataHolder holder;
	int loop;
	
	
	public CalCiteDataQueryThread(DataHolder holder, int loop) {
		this.holder = holder;
		this.loop = loop;
	}

	@Override
	public void run() {
		try {
			//Thread.currentThread().sleep(100);
			System.out.println("Thread " + Thread.currentThread().getId() + " start .");
			long t1 = System.currentTimeMillis();
			for(int i = 0; i < loop ; i++) {
				query();
			}
			long t2 = System.currentTimeMillis();
			System.out.println("Thread " + Thread.currentThread().getId() + " finished in ms :" + (t2 - t1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void query() throws SQLException, ValidationException, SqlParseException, RelConversionException{
		String sql1 = "select count(*) from t1";
		String sql2 = "select count(*) from js.t1";
		System.out.println("sql1 result " + holder.db.exeGetLong(sql1));
		System.out.println("sql2 result " + holder.db.exeGetLong(sql2));
		System.out.println("c1 = " + holder.db.exeGetLong("select max(c1) from js.t1"));
		System.out.println("c2 = " + holder.db.exeGetString( "select c2 from js.t1 limit 1"));
		System.out.println("c3 = " + holder.db.exeGetBoolean( "select c3 from js.t1 limit 1"));
		System.out.println("c4 = " + holder.db.exeGetDecimal( "select c4 from js.t1 limit 1"));
		System.out.println("c5 = " + holder.db.exeGetDate( "select c5 from js.t1 limit 1"));
		System.out.println("c6 = " + holder.db.exeGetTimestamp( "select c6 from js.t1 limit 1"));
		System.out.println("c7 = " + holder.db.exeGetInteger( "select c7 from js.t1 limit 1"));
		System.out.println("c8 as float = " + holder.db.exeGetFloat( "select c8 from js.t1 limit 1"));
		System.out.println("c9 as float = " + holder.db.exeGetFloat( "select c9 from js.t1 limit 1"));
		System.out.println("c8 as double = " + holder.db.exeGetDouble( "select c8 from js.t1 limit 1"));
		System.out.println("c9 as double = " + holder.db.exeGetDouble( "select c9 from js.t1 limit 1"));
		System.out.println("c10 = " +holder.db.exeGetTime( "select c10 from js.t1 limit 1"));
	}

}
