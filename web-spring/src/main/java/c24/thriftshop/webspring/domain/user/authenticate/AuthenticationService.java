package c24.thriftshop.webspring.domain.user.authenticate;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    public AuthenticationResponse execute(final AuthenticationRequest request) {
        final HttpResponse<String> response = Unirest.post("http://localhost:8080/authenticate")
                .header("Authorization", "Bearer " + request.getUsername())
                .asString();
        return new AuthenticationResponse(response.getBody().equals("true"));
    }
}
