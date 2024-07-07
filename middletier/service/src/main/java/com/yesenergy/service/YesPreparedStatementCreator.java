package com.yesenergy.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.PreparedStatementCreator;

public class YesPreparedStatementCreator implements PreparedStatementCreator {

    String sql = null;
    Object[] args;

    public YesPreparedStatementCreator(String sql, Object... args) {
        super();
        this.sql = sql;
        this.args = args;
    }

    public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
        PreparedStatement stmt = conn.prepareCall(sql);
        stmt.setFetchSize(20);
        Integer index = 1;
		if (args != null) {
            for (Object o : args) {
                if (o == null) {
                    stmt.setNull(index, java.sql.Types.NULL);
                } else if (o instanceof Date) {
                        stmt.setTimestamp(index, new java.sql.Timestamp(((Date) o).getTime()));
                } else if (o instanceof String) {
                        stmt.setString(index, (String) o);
                } else if (o instanceof Integer) {
                    stmt.setInt(index, (Integer) o);
                } else if (o instanceof Float) {
                    if (((Float) o).isNaN()) {
                        stmt.setNull(index, java.sql.Types.NULL);
                    } else {
                        stmt.setFloat(index, (Float) o);
                    }
                } else if (o instanceof Double) {
                    if (((Double) o).isNaN()) {
                        stmt.setNull(index, java.sql.Types.NULL);
                    } else {
                        stmt.setDouble(index, (Double) o);
                    }
                } else if (o instanceof BigDecimal) {
                    stmt.setBigDecimal(index, (BigDecimal) o);
                } else {
                    stmt.setString(index, o.toString());
                }
                index++;
            }
        }
        return stmt;
    }

}