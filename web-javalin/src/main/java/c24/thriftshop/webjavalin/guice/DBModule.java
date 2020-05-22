package c24.thriftshop.webjavalin.guice;

import c24.thriftshop.webjavalin.handler.AddHandler;
import c24.thriftshop.webjavalin.persistence.DeviceRepository;
import c24.thriftshop.webjavalin.persistence.HibernateRepository;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import io.javalin.http.Handler;

public class DBModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DeviceRepository.class).to(HibernateRepository.class).asEagerSingleton();
        bind(Handler.class).annotatedWith(Names.named("AddHandler")).to(AddHandler.class).asEagerSingleton();
    }
}