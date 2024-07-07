package com.yesenergy.service;

import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-application-configuration.xml"} )
public class TestStoredProcExecutor {

    @Autowired
	protected IStoredProcExecutor storedProcExecutor;

    @Test
	public void testSPEConnectivity() {
        ArrayList<HashMap<String, Object>> results = storedProcExecutor.fetch("initcap", "abc");
        String actual = (String)results.get(0).get("initcap");
        assertEquals("Abc", actual);
    }
}