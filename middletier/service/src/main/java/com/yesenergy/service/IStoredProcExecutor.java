package com.yesenergy.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface IStoredProcExecutor {
    /**
     * Function to call our postgres database's stored procedures
     * @param storedProcedureName - the name of the stored procedure to call
     * @param arguments - the arguments to pass into the stored proc
     * @return List<Map<String, Object>> - the tabular list of results
     */
    public ArrayList<HashMap<String, Object>> fetch(String storedProcedureName, Object...arguments);
}