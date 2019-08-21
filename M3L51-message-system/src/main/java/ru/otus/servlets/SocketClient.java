/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.servlets;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

@WebSocket
public final class SocketClient {

    private final Lock lock;

    private final BlockingQueue<Session> sessions = new LinkedBlockingQueue<>(1);

    public SocketClient() {
        this(new ReentrantLock());
    }

    public SocketClient(final Lock lock) {
        this.lock = lock;
    }

    @OnWebSocketMessage
    public void message(final String message) {
        System.out.println(message);
    }

    @OnWebSocketConnect
    public void connect(final Session session) throws InterruptedException {
        System.out.println(
            String.format(
                "Client connected to %s",
                session.getRemoteAddress().getAddress()
            )
        );
        this.sessions.put(session);
    }

    public void close() {
        this.sessions.remove();
    }

    public void send(final String message)
        throws IOException, InterruptedException {
        this.lock.lock();
        try {
            final Session session = this.sessions
                .poll(5_000, TimeUnit.MILLISECONDS);
            Objects.requireNonNull(session)
                .getRemote()
                .sendString(message);
            this.sessions.put(session);
        } finally {
            this.lock.unlock();
        }
    }

    public boolean connected() {
        return this.sessions.size() > 0;
    }
}
