package ru.otus.handlers;

import java.util.Objects;
import org.cactoos.Scalar;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.ResourceHandler;

public final class Resource implements Scalar<Handler> {

    private final static String HTML = "public_html";

    @Override
    public Handler value() throws Exception {
        final ResourceHandler handler = new ResourceHandler();
        handler.setResourceBase(
            Objects.requireNonNull(
                Resource.class.getClassLoader().getResource(Resource.HTML)
            ).getPath()
        );
        return handler;
    }
}
