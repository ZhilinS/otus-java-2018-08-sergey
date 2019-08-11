package ru.otus;/*
 * Copyright (C) 2018, SEMRUSH CY LTD or it's affiliates
 */

import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.AdminDataSet;
import ru.otus.dataset.HiberUserDataSet;
import ru.otus.dataset.PhoneDataSet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public final class SessionConfig {

    private final SessionFactory factory;

    public SessionConfig() {
        this("jdbc:postgresql://localhost:5432/otus");
    }

    public SessionConfig(final String url) {
        final Configuration config = new Configuration()
            .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect")
            .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
            .setProperty("hibernate.connection.url", url)
            .setProperty("hibernate.connection.username", "otus")
            .setProperty("hibernate.connection.password", "otus")
            .setProperty("hibernate.show_sql", "true")
            .setProperty("hibernate.connection.useSSL", "false")
            .setProperty("hibernate.enable_lazy_load_no_trans", "true")
            .setProperty("hibernate.hbm2ddl.auto", "validate");
        config.addAnnotatedClass(HiberUserDataSet.class);
        config.addAnnotatedClass(PhoneDataSet.class);
        config.addAnnotatedClass(AddressDataSet.class);
        config.addAnnotatedClass(AdminDataSet.class);
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .applySettings(config.getProperties())
            .build();
        this.factory = config.buildSessionFactory(registry);
//        this.factory = new MetadataSources(registry)
//            .getMetadataBuilder()
//            .build()
//            .getSessionFactoryBuilder()
//            .build();
    }

    public SessionFactory factory() {
        return this.factory;
    }
}
