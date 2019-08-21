/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.servlets;

import java.net.URI;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@WebSocket
public final class NameServlet extends WebSocketServlet {

    private Session session;

    @OnWebSocketConnect
    public void connect(final Session session) {
        System.out.println(
            String.format(
                "Connected to %s",
                session
            )
        );
        this.session = session;
    }

    @OnWebSocketMessage
    public void acquire(final String message) throws Exception {
        final WebSocketClient client = new WebSocketClient();
        final SocketClient socket = new SocketClient();
        try {
            client.start();
            URI echoUri = new URI("ws://localhost:8091/db");
            client.connect(socket, echoUri, new ClientUpgradeRequest());
            System.out.printf("Connecting to: %s", echoUri);
            socket.send(message);
        } finally {
            client.stop();
        }
    }

    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.register(NameServlet.class);
    }
}
