package com.yesenergy.service;

import java.sql.Clob;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.postgresql.jdbc.PgResultSet;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class YesResultSetExtractor implements ResultSetExtractor<ArrayList<HashMap<String, Object>>> {

    @Override
    public ArrayList<HashMap<String, Object>> extractData(ResultSet res) throws SQLException, DataAccessException {
        ResultSetMetaData rsmd = res.getMetaData();
		int numColumns = rsmd.getColumnCount();
		String[] cols = new String[numColumns];
		for (int i = 1; i < numColumns + 1; i++) {
			String columnName = rsmd.getColumnName(i);
			cols[i - 1] = columnName;
		}
			
        ArrayList<HashMap<String, Object>> results = new ArrayList<HashMap<String, Object>>();
		Integer rows = 0;
		// SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		while (res.next()) {
			HashMap<String, Object> row = new HashMap<String, Object>();
			for (String col : cols) {
				Object val = res.getObject(col);
				if (val instanceof Clob) {
                    Clob clobVal = (Clob) val;
    
                    String strVal;
    
                    if (clobVal.length() < Integer.MAX_VALUE) {
                        strVal = clobVal.getSubString(1, (int) clobVal.length());
                        row.put(col, strVal);
                    }
                    // Free the CLOBs!, this helps with PGA memory
                    if (clobVal != null) {
                        clobVal.free();
                    }
    
                } else if (val instanceof PgResultSet) {
                    ArrayList<HashMap<String, Object>> subset = this.extractData((ResultSet)val);
                    results.addAll(subset);
                    rows += subset.size();     
                } else {
                    row.put(col, val);
                }
			}
			results.add(row);
			rows += 1;
		}

		return results;
    }


}
