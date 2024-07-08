package com.yesenergy.api.view;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public abstract class TransactionView extends AbstractView {

    @Override
    protected final void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
                                                 HttpServletResponse response) throws Exception {

        response.setContentType(this.getResponseContentType());

        ArrayList<Object> list = new ArrayList<>();
        for (Map.Entry<?, ?> entry : model.entrySet()) {
            Object obj = entry.getValue();
            if (obj instanceof BindingResult)
                continue;
            if (obj instanceof ArrayList<?>) {
                ArrayList<?> sublist = (ArrayList<?>) obj;
                list.addAll(sublist);
            }
        }

        this.processAndWriteResult(list, response);
    }

    protected String getResponseContentType() {
        return "text/html;charset=utf-8";
    }

    protected void processAndWriteResult(ArrayList<Object> transactions, HttpServletResponse result) throws IOException {
        System.out.println("[WARN]: Method processAndWriteResult not implemented");
    }
}
