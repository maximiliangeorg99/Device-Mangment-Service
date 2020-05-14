import exceptions.AlreadyRegisteredException;
import exceptions.NotAuthorizedException;
import exceptions.WrongPasswordException;
import handler.*;
import io.javalin.Javalin;
import org.apache.logging.log4j.Logger;
import persistence.DeviceRepository;
import persistence.InMemoryDataBase;

import static io.javalin.apibuilder.ApiBuilder.post;
import static org.apache.logging.log4j.LogManager.getLogger;

final class JavalinServer {
    private final static Logger _log = getLogger(JavalinServer.class);
    private final static DeviceRepository deviceRepository = new InMemoryDataBase();

    public static void main(final String[] args) {
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
            }
        });
        app.before("/devices/*", new BeforeHandler());

        app.routes(() -> {
            post("/register", new RegisterHandler());
            post("/user/login", new LoginHandler());
            post("/devices/rent", new RentHandler(deviceRepository));
            post("/devices/return", new ReturnHandler(deviceRepository));
            post("/devices/add", new AddHandler(deviceRepository)); //Admin Role?
        });
    }
}
