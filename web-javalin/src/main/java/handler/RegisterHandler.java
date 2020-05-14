package handler;

import com.google.gson.Gson;
import entity.RegisterRequest;
import exceptions.AlreadyRegisteredException;
import exceptions.WrongPasswordException;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RegisterHandler implements Handler {
    private static final ArrayList<String> users = new ArrayList<>();

    @Override
    public void handle(@NotNull final Context ctx) throws Exception {
        final String body = ctx.body();
        final Gson gson = new Gson();
        final RegisterRequest request = gson.fromJson(body, RegisterRequest.class);
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

