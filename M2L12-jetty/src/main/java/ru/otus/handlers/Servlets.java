package ru.otus.handlers;

import org.cactoos.Scalar;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.otus.servlets.Admin;
import ru.otus.servlets.ErrorServlet;
import ru.otus.servlets.Login;

public final class Servlets implements Scalar<Handler> {

    @Override
    public Handler value() throws Exception {
        final ServletContextHandler handler = new ServletContextHandler(
            ServletContextHandler.SESSIONS
        );
        handler.addServlet(Admin.class, "/admin");
        handler.addServlet(Login.class, "/login");
        handler.addServlet(ErrorServlet.class, "/error");
        return handler;
    }
}
