package com.benmccann.db;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class DbModule extends AbstractModule {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE
            = new ThreadLocal<EntityManager>();

    @Override
    public void configure() {
    }

    @Provides
    @Singleton
    public EntityManagerFactory provideEntityManagerFactory() {
        final Map<String, String> properties = new HashMap<String, String>();
        properties.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.put("hibernate.connection.url", "jdbc:mysql://localhost:3306");
        properties.put("hibernate.connection.username", "c24");
        properties.put("hibernate.connection.password", "Chrono24!");
        properties.put("hibernate.connection.pool_size", "1");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return Persistence.createEntityManagerFactory("db-manager", properties);
    }

    @Provides
    public EntityManager provideEntityManager(final EntityManagerFactory entityManagerFactory) {
        EntityManager entityManager = ENTITY_MANAGER_CACHE.get();
        if (entityManager == null) {
            ENTITY_MANAGER_CACHE.set(entityManager = entityManagerFactory.createEntityManager());
        }
        return entityManager;
    }

}