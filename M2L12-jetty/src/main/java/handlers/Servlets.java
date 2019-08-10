/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package handlers;

import org.cactoos.Scalar;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import servlets.Admin;

public final class Servlets implements Scalar<Handler> {

    @Override
    public Handler value() throws Exception {
        final ServletContextHandler handler = new ServletContextHandler(
            ServletContextHandler.SESSIONS
        );
        handler.addServlet(Admin.class, "/admin");
        return handler;
    }
}
