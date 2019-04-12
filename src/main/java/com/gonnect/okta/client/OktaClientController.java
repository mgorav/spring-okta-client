package com.gonnect.okta.client;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("/source")
@Api("OKTA APIs")
public class OktaClientController {

    private final RestTemplate restTemplate;
    private final ClientCredentialsResourceDetails credentialsResourceDetails;

    @Value("#{ @environment['gonnect.appUrl'] }")
    private String appUrl;

    public OktaClientController(RestTemplate restTemplate, ClientCredentialsResourceDetails credentialsResourceDetails) {
        this.restTemplate = restTemplate;
        this.credentialsResourceDetails = credentialsResourceDetails;
    }

    @PostMapping("/post")
    public ResponseEntity<String> postMessage(String message) {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(message, headers);

        return restTemplate.exchange(appUrl, HttpMethod.POST, entity, String.class);


    }

}