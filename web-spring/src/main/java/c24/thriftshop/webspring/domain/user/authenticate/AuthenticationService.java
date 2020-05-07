package c24.thriftshop.webspring.domain.user.authenticate;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public AuthenticationResponse execute(final AuthenticationRequest request) {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/authenticate")
                .header("Authorization", "Bearer " + request.getToken())
                .asString();
        final JSONObject jsonObject = new JSONObject(response.getBody());
        return new AuthenticationResponse(jsonObject.getBoolean("successful"), jsonObject.getString("id"));
    }
}
