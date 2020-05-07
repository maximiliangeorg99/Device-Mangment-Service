package c24.thriftshop.webspring.domain.user.login;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public LoginResponse execute(final LoginRequest loginRequest) {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/login")
                .header("content-type", "application/json")
                .body("{\n\t\"username\": \"" + loginRequest.getUsername() + "\",\n\t\"password\": \"" + loginRequest.getPassword() + "\"\n}")
                .asString();
        if (response.isSuccess()) {
            return new LoginResponse(true, new JSONObject(response.getBody()).getString("token"));
        } else {
            return new LoginResponse(false, "");
        }
    }
}
