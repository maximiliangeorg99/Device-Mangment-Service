package c24.thriftshop.webjavalin;


import c24.thriftshop.webjavalin.exceptions.AlreadyRegisteredException;
import c24.thriftshop.webjavalin.exceptions.NotAuthorizedException;
import c24.thriftshop.webjavalin.exceptions.WrongPasswordException;
import c24.thriftshop.webjavalin.guice.DBModule;
import c24.thriftshop.webjavalin.handler.*;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import org.apache.logging.log4j.Logger;

import static io.javalin.apibuilder.ApiBuilder.post;
import static org.apache.logging.log4j.LogManager.getLogger;

public final class MainServer {
    private final static Logger _log = getLogger(MainServer.class);
    @Inject
    BeforeHandler beforeHandler;

    @Inject
    @Named("AddHandler")
    Handler addHandler;

    @Inject
    RegisterHandler registerHandler;

    @Inject
    LoginHandler loginHandler;

    @Inject
    RentHandler rentHandler;

    @Inject
    ReturnHandler returnHandler;

    public static void main(final String[] args) {
        final Injector injector = Guice.createInjector(new DBModule());
        final MainServer mainServer = injector.getInstance(MainServer.class);
        mainServer.start();
    }

    public void start() {
        final Javalin app = Javalin.create(config -> {
                    config.accessManager((handler, ctx, permittedRoles) -> handler.handle(ctx));
                    config.enableDevLogging();
                    config.requestLogger((ctx, timeMs) -> {
                        final String message =
                                ctx.method() + " " + ctx.path() + " took " + timeMs + " ms -> " + ctx.status() + (ctx.status() >= 300 && !ctx.resultString().isEmpty() ?
                                        " - " + ctx.resultString() :
                                        "");
                        if (ctx.status() >= 200 && ctx.status() < 400) {
                            _log.info(message);
                        } else if (ctx.status() >= 400 && ctx.status() < 500) {
                            _log.warn(message);
                        } else {
                            _log.error(message);
                        }

                    });
                }
        );
        app.start(8090);
        app.exception(Exception.class, (e, ctx) -> {
            if (e instanceof WrongPasswordException) {
                ctx.status(400).result(e.getMessage());
            } else if (e instanceof AlreadyRegisteredException) {
                ctx.status(500).result(e.getMessage());
            } else if (e instanceof NotAuthorizedException) {
                ctx.status(500).result(e.getMessage());
            } else {
                ctx.status(500).result(("We are sorry, something unexpectedly happened on our side"));
                e.printStackTrace();
            }
        });
        app.before("/devices/*", beforeHandler);

        app.routes(() -> {
            post("/register", registerHandler);
            post("/user/login", loginHandler);
            post("/devices/rent", rentHandler);
            post("/devices/return", returnHandler);
            post("/devices/add", addHandler); //Admin Role?
        });
    }
}
