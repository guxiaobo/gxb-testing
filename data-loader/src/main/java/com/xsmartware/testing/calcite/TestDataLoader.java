package com.xsmartware.testing.calcite;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.calcite.adapter.json.JsonSchema;

import com.alibaba.fastjson.JSONObject;
import com.xsmartware.common.calcite.CalciteDatabase;
import com.xsmartware.common.calcite.DataLoader;

public class TestDataLoader implements DataLoader {

	@Override
	public CalciteDatabase loadData() throws SQLException {
		Map<String, List<JSONObject>> map = makeJsonMap();
		JsonSchema schema = new JsonSchema(map);
		
		return new CalciteDatabase(schema, "js");
	}
	
	private Map<String, List<JSONObject>> makeJsonMap(){
		Map<String, List<JSONObject>> map = new HashMap<String, List<JSONObject>>();
		List<JSONObject> t1 = new ArrayList<JSONObject>();
		JSONObject r1 = new JSONObject();
		t1.add(r1);
		t1.add(r1);
		t1.add(r1);
		map.put("t1", t1);
		
		r1.put("c1", Long.valueOf(100));
		r1.put("c2", "column2");
		r1.put("c3", Boolean.FALSE);
		r1.put("c4", new BigDecimal("2.1"));
		r1.put("c5", LocalDate.now());
		r1.put("c6", LocalDateTime.now());
		r1.put("c7", Integer.valueOf(200));
		r1.put("c8", Double.valueOf(3.43d));
		r1.put("c9", Float.valueOf(2.33f));
		r1.put("c10", LocalTime.now());
		
		return map;
	}

}
