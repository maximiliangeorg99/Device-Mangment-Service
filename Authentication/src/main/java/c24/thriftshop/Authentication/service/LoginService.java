package c24.thriftshop.Authentication.service;

import c24.thriftshop.Authentication.model.AuthResponse;
import c24.thriftshop.Authentication.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.PresentFilter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class LoginService {

    private final LdapTemplate ldapTemplate;
    private final HashMap<String, Date> tokenMap;
    private final HashMap<String, String> tokenToIdMap;

    @Autowired
    public LoginService(final LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
        this.tokenMap = new HashMap<>();
        this.tokenToIdMap = new HashMap<>();
    }

    public AuthResponse authenticate(final String token) {
        boolean s = false;
        if (tokenMap.containsKey(token)) {
            final Date expiration = tokenMap.get(token);
            s = expiration.after(new Date(System.currentTimeMillis()));
        }
        return new AuthResponse(tokenToIdMap.get(token), s);
    }

    public LoginResponse login(final String username, final String password) {
        if (ldapTemplate.authenticate(LdapUtils.emptyLdapName(), new EqualsFilter("uid", username).toString(), password)) {
            final String token = UUID.randomUUID().toString();
            tokenMap.put(token, new Date(System.currentTimeMillis() + 1000 * 3600));
            tokenToIdMap.put(token, username);
            final LoginResponse loginResponse = new LoginResponse(token);
            return loginResponse;
        } else {
            return new LoginResponse("");
        }

    }

    public List<String> search(final String username) {
        return ldapTemplate.search(
                LdapUtils.emptyLdapName(),
                new PresentFilter("uid").toString(),
                (AttributesMapper<String>) attrs -> (String) attrs.get("uid").get());
    }
}