package c24.thriftshop.Authentication.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
public class LdapConfiguration {

    @Bean
    public LdapContextSource contextSource() {
        final LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://ldap.forumsys.com:389");
        contextSource.setBase("dc=example,dc=com");
        contextSource.setUserDn("cn=read-only-admin,dc=example,dc=com");
        contextSource.setPassword("password");
        return contextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() {
        return new LdapTemplate(contextSource());
    }
}