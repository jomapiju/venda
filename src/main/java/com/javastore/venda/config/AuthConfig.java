package com.javastore.venda.config;

import com.javastore.venda.model.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthConfig {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    public String getToken(){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
            requestBody.add("username", environment.getProperty("api_username"));
            requestBody.add("password", environment.getProperty("api_pass"));
            requestBody.add("client_id", environment.getProperty("api_client_id"));
            requestBody.add("grant_type", environment.getProperty("api_grant_type"));
            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
            ResponseEntity<Auth> response = restTemplate.exchange(
                    String.format("%s", environment.getProperty("url_token")),
                    HttpMethod.POST,
                    entity,
                    Auth.class
            );
            Auth auth = response.getBody();
            if (auth != null) {
                return auth.access_token;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
