/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cactoos.func.UncheckedBiFunc;
import templatets.Templates;

public final class Admin extends HttpServlet {

    private static final String AUTHORIZED = "sporijgisdfg";
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
        check(req, resp);
        resp.getWriter().write(
            templates.apply(
                Admin.TEMPLATE,
                new HashMap<>()
            )
        );
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void check(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) {
        Stream.ofNullable(req.getCookies())
            .flatMap(Arrays::stream)
            .filter(cookie -> cookie.getName().equals("authorized"))
            .filter(cookie -> cookie.getValue().equals(Admin.AUTHORIZED))
            .findFirst()
            .ifPresentOrElse(
                cookie -> System.out.println("OK"),
                () -> {
                    resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    try {
                        resp.sendRedirect("/error");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            );
        Optional.ofNullable(
            req.getSession()
                .getAttribute("admin")
        )
            .ifPresentOrElse(
                attr -> {
                    if (attr.equals(false)) {
                        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        try {
                            resp.sendRedirect("/error");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                () -> {
                    resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    try {
                        resp.sendRedirect("/error");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            );
    }
}
