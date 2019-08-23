/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.servlets;

import java.net.URI;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import javax.servlet.ServletException;
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

    private final BlockingQueue<String> messages;
    private final SocketClient socket;
    private final WebSocketClient client;

    public NameServlet() {
        this.messages = new LinkedBlockingQueue<>(1);
        this.socket = new SocketClient(this.messages);
        this.client = new WebSocketClient();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @OnWebSocketConnect
    public void connect(final Session session) throws Exception {
        try {
            this.client.start();
            this.client.connect(
                this.socket,
                new URI(NameServlet.DB_SERVLET),
                new ClientUpgradeRequest()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnWebSocketMessage
    public void onMessage(final Session session, final String message) throws Exception {
        this.socket.send(message);
        session.getRemote().sendString(this.messages.take());
    }

    @OnWebSocketClose
    public void close(final int code, final String message) throws Exception {
        this.socket.close();
        this.client.stop();
    }

    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.register(NameServlet.class);
    }
}
