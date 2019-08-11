package ru.otus.dao;

import ru.otus.HibernateExecutor;
import ru.otus.SessionConfig;
import ru.otus.dataset.HiberUserDataSet;

public final class UserDao {

    private final HibernateExecutor<HiberUserDataSet> executor;

    public UserDao() {
        this(new HibernateExecutor<>(new SessionConfig()));
    }

    public UserDao(final HibernateExecutor<HiberUserDataSet> executor) {
        this.executor = executor;
    }

    public void save(final HiberUserDataSet user) {
        this.executor.save(user);
    }

    public HiberUserDataSet get(final Long id) {
        return this.executor.load(HiberUserDataSet.class, id);
    }

    public HiberUserDataSet byName(final String name) {
        return this.executor.byName(HiberUserDataSet.class, name);
    }
}
