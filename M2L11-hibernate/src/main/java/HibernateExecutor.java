/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */

import models.HiberDataSet;
import org.hibernate.Session;

public final class HibernateExecutor<T extends HiberDataSet> {

    private final SessionConfig config;

    public HibernateExecutor(final SessionConfig config) {
        this.config = config;
    }

    public void save(final T entity) {
        try (final Session session = this.config.factory().openSession()) {
            session.save(entity);
        }
    }

    public <T extends HiberDataSet> T load(final Class<T> clazz, final long id) {
        try (final Session session = this.config.factory().openSession()) {
            return session.load(clazz, id);
        }
    }
}
