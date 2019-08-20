/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.servlets;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/name")
public final class NameServlet {

    @OnMessage
    public void acquire(final Session session, final String message) {
        System.out.println("Message: " + message);
    }
}
