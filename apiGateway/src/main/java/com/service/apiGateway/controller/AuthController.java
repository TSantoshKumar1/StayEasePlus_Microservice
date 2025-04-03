package com.service.apiGateway.controller;

import com.service.apiGateway.models.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client
                                             , @AuthenticationPrincipal OidcUser user

                                             ) {


         ApiResponse authResponse =  new ApiResponse();
         authResponse.setUserId(user.getEmail());
         authResponse.setAccessToken(client.getAccessToken().getTokenValue());
         authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
         authResponse.setExpiresAt(client.getAccessToken().getExpiresAt().getEpochSecond());

        List<String> authorities = user.getAuthorities().stream().map(grantedAuthority -> {

            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());


        authResponse.setAuthorities(authorities);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }
}
