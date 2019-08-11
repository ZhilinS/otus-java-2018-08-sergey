/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */
package ru.otus.dataset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class AdminDataSet extends HiberDataSet {

    @Column(name = "admin", nullable = false)
    private boolean admin;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_id")
    private Long user;

    public AdminDataSet() {
    }

    public AdminDataSet(
        final boolean admin,
        final String password,
        final HiberUserDataSet user
    ) {
        this(admin, password, user.getId());
    }

    public AdminDataSet(
        final boolean admin,
        final String password,
        final Long user
    ) {
        this.admin = admin;
        this.password = password;
        this.user = user;
    }

    public boolean admin() {
        return this.admin;
    }

    public String password() {
        return this.password;
    }
}
