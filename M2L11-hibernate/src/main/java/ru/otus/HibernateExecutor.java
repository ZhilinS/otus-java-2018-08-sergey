package ru.otus;/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.criterion.Restrictions;
import ru.otus.dataset.HiberDataSet;
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

    public <T extends HiberDataSet> T byName(
        final Class<T> clazz,
        final String name
    ) {
        try (final Session session = this.config.factory().openSession()) {
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<T> query = builder.createQuery(clazz);
            final Root<T> root = query.from(clazz);
            return session.createQuery(
                query
                    .select(root)
                    .where(
                        builder.equal(root.get("name"), name)
                    )
            )
                .getResultStream()
                .findFirst()
                .orElseThrow();
        }
    }

    public <T extends HiberDataSet> T byUser(
        final Class<T> clazz,
        final Long user
    ) {
        try (final Session session = this.config.factory().openSession()) {
            final CriteriaBuilder builder = session.getCriteriaBuilder();
            final CriteriaQuery<T> query = builder.createQuery(clazz);
            final Root<T> root = query.from(clazz);
            return session.createQuery(
                query
                    .select(root)
                    .where(
                        builder.equal(root.get("user"), user)
                    )
            )
                .getResultStream()
                .findFirst()
                .orElseThrow();
        }
    }
}
