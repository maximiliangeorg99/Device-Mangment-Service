package c24.thriftshop.Authentication.service;

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
    private final HashMap<String, String> userToTokenMap;

    @Autowired
    public LoginService(final LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
        this.tokenMap = new HashMap<>();
        this.userToTokenMap = new HashMap<>();
    }

    //refresh duration on authenticate?
    public boolean authenticate(final String username) {
        if (tokenMap.containsKey(userToTokenMap.get(username))) {
            final Date expiration = tokenMap.get(userToTokenMap.get(username));
            return expiration.after(new Date(System.currentTimeMillis()));
        }
        return false;
    }

    public LoginResponse login(final String username, final String password) {
        if (ldapTemplate.authenticate(LdapUtils.emptyLdapName(), new EqualsFilter("uid", username).toString(), password)) {
            final String token = UUID.randomUUID().toString();
            tokenMap.put(token, new Date(System.currentTimeMillis() + 1000 * 3600));
            userToTokenMap.put(username, token);
            final LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            return loginResponse;
        } else {
            return new LoginResponse();
        }

    }

    public List<String> search(final String username) {
        return ldapTemplate.search(
                LdapUtils.emptyLdapName(),
                new PresentFilter("uid").toString(),
                (AttributesMapper<String>) attrs -> (String) attrs.get("uid").get());
    }
}