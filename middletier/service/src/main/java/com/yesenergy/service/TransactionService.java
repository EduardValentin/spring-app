package com.yesenergy.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

public class TransactionService implements ITransactionService {

    @Autowired
    private IStoredProcExecutor storedProcExecutor;
    
    public ArrayList<HashMap<String, Object>> getAwardedTransactions() {
        return storedProcExecutor.fetch("get_awarded_transactions");
    }
    
}
