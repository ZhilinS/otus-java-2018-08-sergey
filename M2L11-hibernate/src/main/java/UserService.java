/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */

import models.HiberUserDataSet;

public final class UserService {

    private final HibernateExecutor<HiberUserDataSet> executor;

    public UserService() {
        this(new HibernateExecutor<>(new SessionConfig()));
    }

    public UserService(final HibernateExecutor<HiberUserDataSet> executor) {
        this.executor = executor;
    }

    public void save(final HiberUserDataSet user) {
        this.executor.save(user);
    }

    public HiberUserDataSet get(final Long id) {
        return this.executor.load(HiberUserDataSet.class, id);
    }
}
