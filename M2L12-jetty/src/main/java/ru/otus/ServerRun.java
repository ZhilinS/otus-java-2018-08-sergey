package ru.otus;

import ru.otus.handlers.Resource;
import ru.otus.handlers.Servlets;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;

public class ServerRun {

    private static int PORT = 8090;

    public static void main(String[] args) throws Exception {
//        final Server server = new Server(ServerRun.PORT);
//        server.setHandler(
//            new HandlerList(
//                new Resource().value(),
//                new Servlets().value()
//            )
//        );
//        server.start();
//        server.join();
    }
}
