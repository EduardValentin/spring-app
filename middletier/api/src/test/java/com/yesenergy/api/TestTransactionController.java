package com.yesenergy.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;

import com.yesenergy.api.controllers.TransactionServiceFacade;
import com.yesenergy.api.view.YesJSONView;
import com.yesenergy.service.ITransactionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/test-application-configuration.xml"} )
public class TestTransactionController {
    private MockMvc mockMvc;

    @Mock
    private ITransactionService transactionService;

    @InjectMocks
	private TransactionServiceFacade transactionServiceFacade;

    @Autowired
    private YesJSONView yesview;

    private ArrayList<HashMap<String, Object>> mockedTransactions = new ArrayList<HashMap<String, Object>>() {{
        add(new HashMap<String, Object>() {{
            put("iso", "PJMISO");
            put("ftrparticipant", "test");
            put("participantshortname", "TestParticipant");
            put("tradetype", "BUY");
            put("peaktype", "OFF 7x8");
            put("hedgetype", "OPTION");
            put("sourceid", Long.parseLong("51288"));
            put("sinkid", Long.parseLong("10002503128"));
            put("counterflow", "Y");
            put("contractsize", Float.parseFloat("1.5"));
            put("cost", Float.parseFloat("20.22"));
            put("revenue", Float.parseFloat("20.22"));
            put("profit", Float.parseFloat("0"));
            put("hours", new BigInteger("1176"));
            put("hrs_in_period", new BigInteger("1176"));
        }});
    }};

    public JSONArray getRenderedJson(String urlPath) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(urlPath.toLowerCase()))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn();

        ModelMap model = result.getModelAndView().getModelMap();
        MockHttpServletResponse response = result.getResponse();
        yesview.render(model, result.getRequest(), response);
        String renderedView = response.getContentAsString();
        JSONArray jsonResults = new JSONArray(renderedView);
        return jsonResults;
    }
    
    @Before
    public void init() throws Exception{
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transactionServiceFacade)
                .build();
        when(transactionService.getAwardedTransactions()).thenReturn(mockedTransactions);
    }

    @Test
    public void testRender() throws Exception {
        JSONArray jsonResults = getRenderedJson("/ftr/transactions");
        assertEquals("gets back the expected number of records", mockedTransactions.size(), jsonResults.length());

        assertEquals("Correct portfolioid is in the first slot", "TestParticipant", jsonResults.getJSONObject(0).get("participantshortname"));        
    }
}
