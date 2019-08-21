/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.servlets;

import java.net.URI;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@WebSocket
public final class NameServlet extends WebSocketServlet {

    private static final String DB_SERVLET = "ws://localhost:8091/db";

    private Session session;
    private SocketClient socket;
    private WebSocketClient client;

    @OnWebSocketConnect
    public void connect(final Session session) throws Exception {
        System.out.println(
            String.format(
                "Connected to %s",
                session
            )
        );
        this.session = session;
        this.client = new WebSocketClient();
        this.socket = new SocketClient();
        this.client.start();
        this.client.connect(
            this.socket,
            new URI(NameServlet.DB_SERVLET),
            new ClientUpgradeRequest()
        );
    }

    @OnWebSocketMessage
    public void acquire(final String message) throws Exception {
        this.socket.send(message);
    }

    @OnWebSocketClose
    public void close(final int code, final String message) throws Exception {
        System.out.println(
            String.format(
                "Closing NameWebsocket. %d. %s",
                code,
                message
            )
        );
        this.socket.close();
        this.client.stop();
    }

    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.register(NameServlet.class);
    }
}
