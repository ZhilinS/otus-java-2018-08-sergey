/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.error;

public class EmptyCacheElementException extends RuntimeException {

    public EmptyCacheElementException(final String key) {
        super(
            String.format(
                "Couldn't access cache element by key %s",
                key
            )
        );
    }
}
