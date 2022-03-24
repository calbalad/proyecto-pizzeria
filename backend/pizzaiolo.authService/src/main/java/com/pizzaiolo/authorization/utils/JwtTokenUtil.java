package com.pizzaiolo.authorization.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import static com.pizzaiolo.authorization.utils.Constants.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import io.jsonwebtoken.*;

import com.pizzaiolo.authorization.models.entities.User;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${app.jwt.secret.key}")
    private String jwtSecretKey;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);

        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String createTokenFromAuth(Authentication authentication, String role) {
        return generateToken(authentication.getName(), role);
    }

    public String createTokenFromUser(User user) {
        return generateToken(user.getEmail(), user.getRole().toString());
    }

    private String generateToken(String username, String role) {
        long currentTimestampInMillis = System.currentTimeMillis();
        List<GrantedAuthority> grantedAuthorities = "admin".compareToIgnoreCase(username) == 0 ?
				AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER, ROLE_ADMIN") :
				AuthorityUtils.createAuthorityList(role);
//        return Jwts.builder()
//		.setId("MicroserviciosJWT")
//		.setSubject(username)
//		.claim(AUTHORITIES_KEY, role)
//		.setIssuedAt(new Date(System.currentTimeMillis()))
//		.setExpiration(new Date(System.currentTimeMillis() + 600000))
//		.signWith(SignatureAlgorithm.HS512,
//				jwtSecretKey.getBytes()).compact();

        return Jwts.builder()
                .setSubject(username)
                .claim(AUTHORITIES_KEY, grantedAuthorities.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
                .signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                .setIssuedAt(new Date(currentTimestampInMillis))
                .setExpiration(new Date(currentTimestampInMillis + (TOKEN_LIFETIME_SECONDS * 1000)))
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
