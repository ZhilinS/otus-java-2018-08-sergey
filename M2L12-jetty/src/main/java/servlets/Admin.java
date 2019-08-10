/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cactoos.func.UncheckedBiFunc;
import templatets.Templates;

public final class Admin extends HttpServlet {

    private static final String TEMPLATE = "admin.ftl";

    private final UncheckedBiFunc<String, Map<String, Object>, String> templates;

    public Admin() throws IOException {
        this(
            new UncheckedBiFunc<>(
                new Templates()
            )
        );
    }

    public Admin(
        final UncheckedBiFunc<String, Map<String, Object>, String> templates
    ) {
        this.templates = templates;
    }

    @Override
    protected void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws IOException {
        resp.getWriter().write(
            templates.apply(
                Admin.TEMPLATE,
                new HashMap<>()
            )
        );
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
