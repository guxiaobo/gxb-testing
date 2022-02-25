package com.xsmartware.common.calcite;

import java.sql.ResultSet;
import java.sql.Statement;
import org.apache.calcite.jdbc.CalciteConnection;

public class CalCiteTestThread extends Thread{
	CalciteConnection conn;
	int  loop;
	public final String sql1 = "select d.deptno, min(e.empid) "
		    + "from hr.emps as e "
		    + "join hr.depts as d "
		    + "  on e.deptno = d.deptno "
		    + "group by d.deptno "
		    + "having count(*) > 1";
	
	public final String sql2 = "select e.deptno, min(e.empid) "
			+ " from hr.emps as e"
			+ " group by e.deptno ";
	
	public final String sql3 = "select count(e.deptno), min(e.empid) "
			+ " from hr.emps as e";
	
	
	
	public CalCiteTestThread(CalciteConnection conn, int loop) {
		this.conn = conn;
		this.loop = loop;
	}

	@Override
	public void run() {
		try {
			//Thread.currentThread().sleep(100);
			System.out.println("Thread " + Thread.currentThread().getId() + " start .");
			long t1 = System.currentTimeMillis();
			for(int i = 0; i < loop ; i++) {
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql1);
				//while(rs.next()) {
					//System.out.println("Got a row from thread :" + Thread.currentThread().getId());
				//}
				rs.close();
				statement.close();
			}
			long t2 = System.currentTimeMillis();
			System.out.println("Thread " + Thread.currentThread().getId() + " finished in ms :" + (t2 - t1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
