package c24.thriftshop.webjavalin.guice;

import c24.thriftshop.webjavalin.persistence.DeviceRepository;
import c24.thriftshop.webjavalin.persistence.HibernateRepository;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class DBModule extends AbstractModule {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<EntityManager>();

    @Override
    protected void configure() {
        bind(DeviceRepository.class).to(HibernateRepository.class).asEagerSingleton();
    }

    @Provides
    @Singleton
    public EntityManagerFactory provideEntityManagerFactory() {
        final Map<String, String> properties = new HashMap<String, String>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        properties.put("javax.persistence.jdbc.driver", "com.mysql.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306");
        properties.put("javax.persistence.jdbc.user", "c24");
        properties.put("javax.persistence.jdbc.password", "Chrono24!");
        properties.put("hibernate.connection.pool_size", "1");
        return Persistence.createEntityManagerFactory("web-javalin", properties);
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