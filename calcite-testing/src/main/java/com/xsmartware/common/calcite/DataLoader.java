package com.xsmartware.common.calcite;

import java.sql.SQLException;

public interface DataLoader {
	
	CalciteDatabase loadData()throws SQLException;
}
