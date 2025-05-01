package com.labelapp.auth_service.config.security.service;

import com.labelapp.auth_service.config.security.UserDetailsImplementation;
import com.labelapp.auth_service.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JWTService {
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.accessTokenValiditityMS}")
    private int jwtExpirationMs;

    @Value("${jwt.cookieName}")
    private String jwtCookie;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public ResponseCookie generateJwtCookie(UserDetailsImplementation userPrincipal) {
        String jwt = generateTokenForUser(userPrincipal.getUser());
        return ResponseCookie.from(jwtCookie, jwt).maxAge(60 * 60).httpOnly(true).build();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);
            return true;
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return false;
    }

    public String generateTokenForUser(User user) {
        Map<String, Object> authRoles = new HashMap<>();
        authRoles.put("roles", List.of(user.getRole().toString()));

        return Jwts.builder().setClaims(authRoles).setSubject(user.getEmail()).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)).signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    public List<SimpleGrantedAuthority> getAuthorities(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        List<String> roles = claims.get("roles", List.class);

        if (roles == null) {
            return List.of();
        }

        return roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }



}