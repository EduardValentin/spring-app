package com.yesenergy.api.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class YesJSONView extends TransactionView {

    private ThreadLocal<ObjectMapper> objectMapper = new ThreadLocal<ObjectMapper>();

    public YesJSONView() {
        this.setContentType("application/json");
        ObjectMapper localMapper = objectMapper.get();
        if (localMapper == null) {
            localMapper = new ObjectMapper();
            objectMapper.set(localMapper);
        }
    }

    @Override
    protected String getResponseContentType() {
        return "application/json;charset=utf-8";
    }

    @Override
    protected void processAndWriteResult(ArrayList<Object> list, HttpServletResponse response) throws IOException {
        lazyGetObjectMapper().writeValue(response.getOutputStream(), list);
    }

    private ObjectMapper lazyGetObjectMapper() {
        ObjectMapper localMapper = objectMapper.get();
        if (localMapper == null) {
            localMapper = new ObjectMapper();
            objectMapper.set(localMapper);
        }
        return localMapper;
    }
}
