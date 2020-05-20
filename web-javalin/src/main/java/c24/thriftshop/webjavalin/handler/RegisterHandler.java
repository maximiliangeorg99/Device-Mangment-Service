package c24.thriftshop.webjavalin.handler;

import c24.thriftshop.webjavalin.entity.RegisterRequest;
import c24.thriftshop.webjavalin.exceptions.AlreadyRegisteredException;
import c24.thriftshop.webjavalin.exceptions.WrongPasswordException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RegisterHandler implements Handler {
    private static final ArrayList<String> users = new ArrayList<>();

    @Inject
    public RegisterHandler() {
    }

    @Override
    public void handle(@NotNull final Context ctx) throws Exception {
        final String body = ctx.body();
        final RegisterRequest request = new ObjectMapper().readValue(body, RegisterRequest.class);
        final String name = request.getName();
        final String p1 = request.getPw1();
        final String p2 = request.getPw2();
        if (users.contains(name))
            throw new AlreadyRegisteredException("There is already a user with this name");
        if (!p1.equals(p2))
            throw new WrongPasswordException("The entered passwords are not identical");
        users.add(name);
        ctx.result("Hello: " + name);
    }
}

