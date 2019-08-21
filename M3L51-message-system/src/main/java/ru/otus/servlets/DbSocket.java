/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.servlets;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@WebSocket
public final class DbSocket extends WebSocketServlet {

    private Session session;

    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.register(DbSocket.class);
    }

    @OnWebSocketConnect
    public void connect(final Session session) {
        System.out.println(
            String.format(
                "DB socket Connected to %s",
                session.getRemoteAddress().getAddress()
            )
        );
        this.session = session;
    }

    @OnWebSocketMessage
    public void message(final String message) {
        System.out.println(
            String.format(
                "DB servlet message: %s",
                message
            )
        );
    }
}
