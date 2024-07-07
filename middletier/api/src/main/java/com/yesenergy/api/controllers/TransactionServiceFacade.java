package com.yesenergy.api.controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yesenergy.service.ITransactionService;

@Controller
public class TransactionServiceFacade implements ITransactionServiceFacade {

    @Autowired
    private ITransactionService transactionService;

	@RequestMapping(method = RequestMethod.GET, value = "/ftr/transactions")
    public ArrayList<HashMap<String, Object>> getTransactionList() {
        return transactionService.getAwardedTransactions();
    }
    
}
