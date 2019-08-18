/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.cactoos.func.UncheckedBiFunc;
import org.cactoos.map.MapEntry;
import org.cactoos.map.MapOf;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.beans.TimeService;
import ru.otus.templatets.Templates;

public final class Timer extends HttpServlet {

    private static final String TEMPLATE = "timer.ftl";
    private static final String REFRESH_VAR = "refreshPeriod";
    private static final String TIME_VAR = "time";
    private static final int PERIOD = 1000;

    private TimeService time;

    private final UncheckedBiFunc<String, Map<String, Object>, String> templates;

    public Timer() throws IOException {
        this(
            new UncheckedBiFunc<>(
                new Templates()
            )
        );
    }

    public Timer(
        final UncheckedBiFunc<String, Map<String, Object>, String> templates
    ) {
        this.templates = templates;
    }

    public void init() {
        final ApplicationContext context = new ClassPathXmlApplicationContext(
            "SpringBeans.xml"
        );
        this.time = context.getBean("timer", TimeService.class);
    }

    @Override
    protected void doGet(
        final HttpServletRequest req,
        final HttpServletResponse resp
    ) throws ServletException, IOException {
        resp.getWriter().write(
            templates.apply(
                Timer.TEMPLATE,
                new MapOf<>(
                    new MapEntry<>(Timer.REFRESH_VAR, Timer.PERIOD),
                    new MapEntry<>(Timer.TIME_VAR, this.time.time())
                )
            )
        );
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
