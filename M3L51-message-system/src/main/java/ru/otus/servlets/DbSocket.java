/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.servlets;

import javax.servlet.ServletException;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import ru.otus.HibernateExecutor;
import ru.otus.SessionConfig;
import ru.otus.dao.UserDao;
import ru.otus.dataset.HiberUserDataSet;

@WebSocket
public final class DbSocket extends WebSocketServlet {

    private final UserDao users;

    public DbSocket() {
        this.users = new UserDao(
            new HibernateExecutor<>(
                new SessionConfig()
            )
        );
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.register(DbSocket.class);
    }

    @OnWebSocketMessage
    public void message(final Session session, final String message)
        throws Exception {
        final HiberUserDataSet user = this.users.byName(message);
        session.getRemote().sendString(String.valueOf(user.getId()));
    }
}
