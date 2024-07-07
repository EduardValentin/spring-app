package com.yesenergy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-application-configuration.xml"} )
public class TestTransactionService {
    
    @Autowired
	protected ITransactionService transactionService;

    @Test
	public void testGetAwardedTransactions() {
        ArrayList<HashMap<String, Object>> results = transactionService.getAwardedTransactions();
        assertNotNull(results);
        //this is bad form, but since I control the ammount of data it's "fine"
        assertEquals(5000, results.size());
        HashMap<String, Object> firstRow = results.get(0);
        //verify we're getting the types we want
        assertTrue(firstRow.get("ftrparticipant") instanceof String);
        assertTrue(firstRow.get("sourceid") instanceof Long);
        assertTrue(firstRow.get("contractsize") instanceof Float);

    }
}
