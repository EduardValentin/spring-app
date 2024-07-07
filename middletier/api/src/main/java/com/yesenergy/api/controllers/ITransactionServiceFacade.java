package com.yesenergy.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;

public interface ITransactionServiceFacade {

    /**
     * Returns the entire list of FTR Transactions from the database
     * @return ArrayList<HashMap<String, Object>> list of rows from the db
     */
    public ArrayList<HashMap<String, Object>> getTransactionList();
}
