package com.naresh.authservice.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Our configuration for the OAuth2 Authorization Server.
 */
@Configuration
@EnableAuthorizationServer
// The Resource Server configuration creates a security filter with '@Order(3)' by default,
// so by moving the authorization server to '@Order(6)' we ensure that the rule for '/user' takes precedence.
@Order(6)
public class AuthServerOAuth2Config extends AuthorizationServerConfigurerAdapter {
    
    private final AuthenticationManager authenticationManager;
    
    @Autowired
    private DataSource dataSource;
    
    @Autowired
    public AuthServerOAuth2Config(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        /*
         * Allow our tokens to be delivered from our token access point as well as for tokens
         * to be validated from this point
         */
        security.checkTokenAccess("permitAll()");
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore()); // Persist the tokens in the database
    }
    
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }
}

// Reference: https://bitbucket.org/rlippolis/cloud-security-example/src/aabcac9a80b3146c90a88fe84073c51083048143/my-auth-server/src/main/java/com/jdriven/example/cloudsecurity/config/AuthorizationServerConfigurer.java?at=master