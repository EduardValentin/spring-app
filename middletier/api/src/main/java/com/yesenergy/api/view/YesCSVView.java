package com.yesenergy.api.view;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class YesCSVView extends TransactionView {
    public YesCSVView() {
        this.setContentType("text/csv");
    }

    @Override
    protected String getResponseContentType() {
        return "application/csv;charset=utf-8";
    }

    @Override
    protected void processAndWriteResult(ArrayList<Object> transactions, HttpServletResponse response) throws IOException {

        StringBuilder builder = new StringBuilder();

        Object firstRow = transactions.get(0);
        String[] keys = assertHashMap(firstRow)
                .keySet()
                .stream()
                .map(Object::toString)
                .toArray(String[]::new);

        this.processAndWriteRow(keys, builder, key -> key);

        for (Object row : transactions) {
            this.processAndWriteRow(keys, builder, assertHashMap(row)::get);
        }

        ServletOutputStream outstream = response.getOutputStream();
        String content = builder.toString();
        outstream.print(content);
    }

    protected HashMap<?, ?> assertHashMap(Object row) {
        if (!(row instanceof HashMap<?, ?>)) {
            throw new IllegalStateException("Unexpected model data structure");
        }
        return (HashMap<?, ?>) row;
    }

    private void processAndWriteRow(String[] keys, StringBuilder builder, Function<String, Object> valueProvider) {
        int i = 0;
        for (String key : keys) {
            builder.append(valueProvider.apply(key));
            i = i + 1;
            if (i < keys.length) {
                builder.append(",");
            }
        }
        builder.append("\n");
    }
}
