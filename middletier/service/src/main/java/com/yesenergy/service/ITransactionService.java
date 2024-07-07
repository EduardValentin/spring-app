package com.yesenergy.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface ITransactionService {
    /**
     * Service wrapper that will call the stored procedure get_awarded_transactions
     * that will pull ALL awarded transactions and their associated participants
     * @return ArrayList<HashMap<String,Object>>
     */
    public ArrayList<HashMap<String,Object>> getAwardedTransactions();
}
