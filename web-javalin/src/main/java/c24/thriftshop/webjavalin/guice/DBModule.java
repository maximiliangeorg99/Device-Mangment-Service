package c24.thriftshop.webjavalin.guice;

import c24.thriftshop.webjavalin.persistence.DeviceRepository;
import c24.thriftshop.webjavalin.persistence.HibernateRepository;
import com.google.inject.AbstractModule;

import javax.persistence.EntityManager;

public class DBModule extends AbstractModule {

    private static final ThreadLocal<EntityManager> ENTITY_MANAGER_CACHE = new ThreadLocal<>();

    @Override
    protected void configure() {
        bind(DeviceRepository.class).to(HibernateRepository.class).asEagerSingleton();
    }
}