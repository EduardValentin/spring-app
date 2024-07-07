package com.yesenergy.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class StoredProcExecutor extends JdbcDaoSupport implements IStoredProcExecutor {

    private String getParameterSQLString(Object... arguments) {
        String str = "";
		if (arguments != null && arguments.length > 0) {
			str = "(";
			for (int index = 0;  index < arguments.length; index++) {
				str += "?,";
			}
			str = str.substring(0, str.length() - 1);
			str += ")";
		} else {
			str = "()";
		}
		return str;
    }

    public ArrayList<HashMap<String, Object>> fetch(String storedProcedureName, Object...arguments) {
        ArrayList<HashMap<String, Object>> results;
        String sql = "select " + storedProcedureName + getParameterSQLString(arguments);
        results = this.getJdbcTemplate().query(new YesPreparedStatementCreator(sql, arguments),
        new YesResultSetExtractor());

        return results;
    }
    
}