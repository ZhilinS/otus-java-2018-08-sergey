package ru.otus.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeService {

    private static final String DEFAULT_PATTERN = "HH:mm:ss";

    private String pattern;

    public TimeService() {
        this(TimeService.DEFAULT_PATTERN);
    }

    public TimeService(final String pattern) {
        this.pattern = pattern;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public String time() {
        final Date date = new Date();
        final DateFormat format = new SimpleDateFormat(this.pattern);
        return format.format(date);
    }
}
