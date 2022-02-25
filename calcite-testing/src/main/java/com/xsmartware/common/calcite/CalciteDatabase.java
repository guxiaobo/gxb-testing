package com.xsmartware.common.calcite;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Properties;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.rel.RelRoot;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.tools.FrameworkConfig;
import org.apache.calcite.tools.Frameworks;
import org.apache.calcite.tools.Planner;
import org.apache.calcite.tools.RelConversionException;
import org.apache.calcite.tools.RelRunner;
import org.apache.calcite.tools.ValidationException;

public class CalciteDatabase {
	static {
		try {
			Class.forName("org.apache.calcite.jdbc.Driver");
		}catch(Throwable t ) {
			throw new RuntimeException(t);
		}
	}
	
	private final CalciteConnection conn;
	private final FrameworkConfig config;
	
	
	public CalciteDatabase(Schema schema, String schemaName)
			throws SQLException {
		assert (schemaName != null && !schemaName.isBlank());
		
		conn = openConnection();
		conn.getRootSchema().add(schemaName, schema);
		
		config = Frameworks.newConfigBuilder()
		.defaultSchema(conn.getRootSchema().getSubSchema(schemaName))
		.parserConfig(SqlParser.config().withCaseSensitive(false))
		.build();
	}
	
	public SchemaPlus getRootSchema() {
		return config.getDefaultSchema().getParentSchema();
	}
	
	public SchemaPlus getDefaultSchema() {
		return config.getDefaultSchema();
	}
	
	
	private CalciteConnection openConnection() throws SQLException{
		Properties info = new Properties();
		info.setProperty("lex", "JAVA");
		Connection connection = DriverManager.getConnection("jdbc:calcite:", info);
		CalciteConnection conn = connection.unwrap(CalciteConnection.class);		
		return conn;
	}
	
	public ResultSet executeQuery(String sql) 
			throws SQLException, ValidationException, 
			SqlParseException, RelConversionException{
		return executeQuery(parseSql(sql));
	}
	
	public ResultSet executeQuery(RelNode relRoot) throws SQLException {
        
        final RelRunner runner = conn.unwrap(RelRunner.class);
        ResultSet resultSet = runner.prepareStatement(relRoot).executeQuery();
        return resultSet;
    }

    public RelNode parseSql(String sql)
            throws SQLException, ValidationException, 
            SqlParseException, RelConversionException {
    	final Planner planner = Frameworks.getPlanner(config);
        final SqlNode sqlNode = planner.parse(sql);
        final SqlNode validated = planner.validate(sqlNode);
        final RelRoot relRoot = planner.rel(validated);
        return relRoot.project();
    }
    
    public  String exeGetString(String sql) 
			throws SQLException, ValidationException, 
			SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		String ret = null;
		if(rs.next()) {
			ret = rs.getString(1);
		}
		rs.close();
		return ret;
	}
    
    public  Long exeGetLong(String sql) 
    		throws SQLException, ValidationException, 
    		SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		Long ret = null;
		if(rs.next()) {
			ret = rs.getLong(1);
		}
		rs.close();
		return ret;
	}
    
    public  Integer exeGetInteger(String sql) 
    		throws SQLException, ValidationException, 
    		SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		Integer ret  = null;
		if(rs.next()) {
			ret = rs.getInt(1);
		}
		rs.close();
		return ret;
	}
    
    public  BigDecimal exeGetDecimal(String sql) 
    		throws SQLException, ValidationException, 
    		SqlParseException, RelConversionException {
    	ResultSet rs = executeQuery(sql);
		BigDecimal ret = null;
		if(rs.next()) {
			ret = rs.getBigDecimal(1);
		}
		rs.close();
		return ret;
	}
    
    public  Float exeGetFloat(String sql) 
			throws SQLException, ValidationException, 
			SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		Float ret = null;
		if(rs.next()) {
			ret = rs.getFloat(1);
		}
		rs.close();
		return ret;
	}
    public  Double exeGetDouble(String sql) 
			throws SQLException, ValidationException, 
			SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		Double ret = null;
		if(rs.next()) {
			ret = rs.getDouble(1);
		}
		rs.close();
		return ret;
	}
	
	public  Boolean exeGetBoolean(String sql) 
			throws SQLException, ValidationException, 
			SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		Boolean ret = null;
		if(rs.next()) {
			ret = rs.getBoolean(1);
		}
		rs.close();
		return ret;
	}
	
	public  Date exeGetDate(String sql) 
			throws SQLException, ValidationException, 
			SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		Date ret = null;
		if(rs.next()) {
			ret = rs.getDate(1);
		}
		rs.close();
		return ret;
	}
	
	public  Time exeGetTime(String sql) 
			throws SQLException, ValidationException, 
			SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		Time ret = null;
		if(rs.next()) {
			ret = rs.getTime(1);
		}
		rs.close();
		return ret;
	}
	
	public  Timestamp exeGetTimestamp(String sql) 
			throws SQLException, ValidationException, 
			SqlParseException, RelConversionException {
		ResultSet rs = executeQuery(sql);
		Timestamp ret = null;
		if(rs.next()) {
			ret = rs.getTimestamp(1);
		}
		rs.close();
		return ret;
	}
	
	
	

}
