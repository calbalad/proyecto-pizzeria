package com.pizzaiolo.authorization.models.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@AllArgsConstructor
@Setter
@Getter
public class AuthTokenResponse {
    private String accessToken;

    private String refreshToken;
    
    private String role;

    private long expiresIn;
}


