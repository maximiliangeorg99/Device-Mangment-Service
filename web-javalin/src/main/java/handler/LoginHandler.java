package handler;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.NotNull;

public class LoginHandler implements Handler {
    @Override
    public void handle(@NotNull final Context ctx) throws Exception {
        final String body = ctx.body();
        final JSONObject jsonObject = new JSONObject(body);
        //redirect to the Authentication
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/login")
                .header("content-type", "application/json")
                .body("{\n\t\"username\": \"" + jsonObject.getString("username") + "\",\n\t\"password\": \"" + jsonObject.getString("password") + "\"\n}")
                .asString();
        ctx.result(response.getBody());
    }
}
