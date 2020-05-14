package handler;

import exceptions.NotAuthorizedException;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.jetbrains.annotations.NotNull;

public class BeforeHandler implements Handler {
    @Override
    public void handle(@NotNull final Context ctx) throws Exception {
        final String header = ctx.header("Authorization");
        final String token = header.replaceFirst("Bearer ", "");
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/authenticate")
                .header("Authorization", "Bearer " + token)
                .asString();
        final JSONObject jsonObject = new JSONObject(response.getBody());
        if (!jsonObject.getBoolean("successful"))
            throw new NotAuthorizedException("Not Authorized");
        ctx.attribute("userId", jsonObject.getString("id"));
    }
}
