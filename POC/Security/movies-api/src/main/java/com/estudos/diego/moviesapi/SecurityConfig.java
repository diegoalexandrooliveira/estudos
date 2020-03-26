package com.estudos.diego.moviesapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.box-office.client-id}")
    private String clientId;
    @Value("${spring.security.oauth2.box-office.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.box-office.accessTokenUri}")
    private String tokenUri;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
    }

    @Bean
    public OAuth2RestTemplate restTemplate(){

        ClientCredentialsResourceDetails resourceOwnerPasswordResourceDetails = new ClientCredentialsResourceDetails();
        resourceOwnerPasswordResourceDetails.setClientId(clientId);
        resourceOwnerPasswordResourceDetails.setClientSecret(clientSecret);
        resourceOwnerPasswordResourceDetails.setAccessTokenUri(tokenUri);
        resourceOwnerPasswordResourceDetails.setClientAuthenticationScheme(AuthenticationScheme.form);
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resourceOwnerPasswordResourceDetails);
        oAuth2RestTemplate.getAccessToken();
        return oAuth2RestTemplate;

    }

}


