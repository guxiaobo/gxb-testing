package com.xsmartware.testing.calcite;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.calcite.adapter.java.ReflectiveSchema;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.tools.RelConversionException;
import org.apache.calcite.tools.ValidationException;
import org.junit.Test;
import com.xsmartware.common.calcite.CalciteDatabase;

/***
 * using janino 3.1.4 will cause the following error
 * 
 * Caused by: org.codehaus.commons.compiler.InternalCompilerException: Operand stack underflow
 * @author linda
 *
 */
public class Test1 {
	
	public class DataObjSchema{
		public DataObj[] t1;
		public DataObjSchema(DataObj[] data) {
			this.t1 = data;
		}
		
	}
	
	public DataObj[] data = {
			new DataObj(1L, "id1", new Date(), new BigDecimal(23.45), Boolean.FALSE),
			new DataObj(2L, "id2", new Date(), new BigDecimal(1.345), Boolean.TRUE)
			
	};
	
	
	@Test
	public void Test1() throws SQLException, ValidationException, 
	SqlParseException, RelConversionException {
		
		Schema schema = new ReflectiveSchema(new DataObjSchema(data));
		
		CalciteDatabase db = new CalciteDatabase(schema, "s");
		System.out.println("result 1 :" + db.exeGetLong("select max(id) from t1"));
		System.out.println("result 2 :" + db.exeGetString("select name from t1 order by name desc limit 1"));
		System.out.println("result 3 :" + db.exeGetTimestamp("select max(time) from t1 "));
		System.out.println("result 4 :" + db.exeGetDecimal("select avg(amount) from t1 "));
		System.out.println("result 5 :" + db.exeGetBoolean("select flag from t1 order by name asc limit 1 "));
		
		
		
	}

}
